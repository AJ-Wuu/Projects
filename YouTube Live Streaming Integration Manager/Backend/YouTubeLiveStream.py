'''@author AJWuu'''

from datetime import date, datetime, timedelta
import time
import os
import requests
import json
import pickle
from googleapiclient.discovery import build
from google_auth_oauthlib.flow import InstalledAppFlow
from google.auth.transport.requests import Request
import google_auth_oauthlib.flow
import googleapiclient.discovery
import googleapiclient.errors
import schedule

"""
Preparation for this project:
1. Get the credential file from https://console.cloud.google.com/apis -> Credentials -> Download OAuth Client
2. Go to https://accounts.google.com/o/oauth2/auth?response_type=code&client_id=330008079846-2q81f3b9t5944jqfhrbiaihkv33gltcs.apps.googleusercontent.com&redirect_uri=urn%3Aietf%3Awg%3Aoauth%3A2.0%3Aoob&scope=https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fyoutube&state=K4nH8YUMZAYOqYJwmzg5jUfP0GQnUH&access_type=offline&prompt=consent&include_granted_scopes=true
   to get the initial code, paste it in the parameter of schedule()
3. Add the calendar synchronization to HELO
   (Google Calendar -> Setting -> Settings for my calendars -> Access permisions for events -> Make available to public
                                                            -> Integrate calendar -> Public address in iCal format)
4. Connect the HELO to YouTube account (add Stream Key and Stream URL)
5. Delete previous .pickle when resume the program
"""


CREDENTIALS_FILE = "WednesdayNiteCredentials.json"

calendarToken = "calendarToken.pickle"
youtubeToken = "youtubeToken.pickle"
CredentialCode = ""
startTimeBuffer = 2  # start live 2 minutes ahead of the schedule
endTimeBuffer = 1  # end live 1 minute behind the schedule


def accessGoogleCalendarAPIService():
    # If modifying these scopes, delete the file token.pickle
    SCOPES = ['https://www.googleapis.com/auth/calendar']
    creds = None
    # The file token.pickle stores the user's access and refresh tokens.
    # It is created automatically when the authorization flow completes for the first time.

    if os.path.exists(calendarToken):
        with open(calendarToken, 'rb') as token:
            creds = pickle.load(token)
    # If there are no (valid) credentials available, let the user log in.
    if not creds or not creds.valid:
        if creds and creds.expired and creds.refresh_token:
            creds.refresh(Request())
        else:
            flow = InstalledAppFlow.from_client_secrets_file(
                CREDENTIALS_FILE, SCOPES)
            creds = flow.run_local_server(port=0)

        # Save the credentials for the next run
        with open(calendarToken, 'wb') as token:
            pickle.dump(creds, token)

    service = build('calendar', 'v3', credentials=creds)
    return service


def accessYouTubeLiveStreamingAPIService():
    SCOPES = ["https://www.googleapis.com/auth/youtube"]
    # scopes has to contain at least one of the following:
    # https://www.googleapis.com/auth/youtube
    # https://www.googleapis.com/auth/youtube.force-ssl

    # Disable OAuthlib's HTTPS verification when running locally.
    # *DO NOT* leave this option enabled in production.
    #os.environ["OAUTHLIB_INSECURE_TRANSPORT"] = "1"

    creds = None
    # The file token.pickle stores the user's access and refresh tokens.
    # It is created automatically when the authorization flow completes for the first time.
    if os.path.exists(youtubeToken):
        with open(youtubeToken, 'rb') as token:
            creds = pickle.load(token)
    # If there are no (valid) credentials available, let the user log in.
    if not creds or not creds.valid:
        if creds and creds.expired and creds.refresh_token:
            creds.refresh(Request())
        else:
            flow = google_auth_oauthlib.flow.InstalledAppFlow.from_client_secrets_file(
                CREDENTIALS_FILE,
                SCOPES,
                redirect_uri='urn:ietf:wg:oauth:2.0:oob'
            )
            authorization_url, state = flow.authorization_url(
                access_type='offline', prompt='consent', include_granted_scopes='true')
            #print('please go to this URL: {}'.format(authorization_url))
            # https://accounts.google.com/o/oauth2/auth?response_type=code&client_id=330008079846-2q81f3b9t5944jqfhrbiaihkv33gltcs.apps.googleusercontent.com&redirect_uri=urn%3Aietf%3Awg%3Aoauth%3A2.0%3Aoob&scope=https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fyoutube&state=K4nH8YUMZAYOqYJwmzg5jUfP0GQnUH&access_type=offline&prompt=consent&include_granted_scopes=true
            code = CredentialCode
            flow.fetch_token(code=code)
            creds = flow.credentials

        # Save the credentials for the next run
        with open(youtubeToken, 'wb') as token:
            pickle.dump(creds, token)

    youtube = googleapiclient.discovery.build(
        'youtube', 'v3', credentials=creds)
    return youtube


def moveStartTimeForwards(start):
    startTime = (datetime.strptime(
        start[11:19], '%H:%M:%S') - timedelta(minutes=startTimeBuffer)).strftime("%H:%M:%S")
    start = start[:11] + startTime + start[19:]
    return start


def moveEndTimeBackwards(end):
    endTime = (datetime.strptime(
        end[11:19], '%H:%M:%S') + timedelta(minutes=endTimeBuffer)).strftime("%H:%M:%S")
    end = end[:11] + endTime + end[19:]
    return end


def calendarGetEventID(targetEvent):
    service = accessGoogleCalendarAPIService()
    page_token = None
    while True:
        events = service.events().list(calendarId='primary', pageToken=page_token).execute()
        for tempEvent in events['items']:
            if ((tempEvent['summary'] == targetEvent['title']) and (tempEvent['start']['dateTime'] == targetEvent['startDate8601']) and (tempEvent['end']['dateTime'] == targetEvent['endDate8601'])):
                return tempEvent['id']
        page_token = events.get('nextPageToken')
        if not page_token:
            break


def calendarInsert(todayEvent):
    service = accessGoogleCalendarAPIService()
    request = service.events().insert(
        calendarId='primary',
        body={
            'summary': todayEvent.get('title'),
            'start': {"dateTime": moveStartTimeForwards(todayEvent.get('startDate8601'))},
            'end': {"dateTime": moveEndTimeBackwards(todayEvent.get('endDate8601'))}
        }
    )
    request.execute()


def calendarUpdate(todayEvent, existedEvent):
    service = accessGoogleCalendarAPIService()
    try:
        request = service.events().update(
            calendarId='primary',
            eventId=calendarGetEventID(existedEvent),
            body={
                "summary": todayEvent.get('title'),
                'start': {"dateTime": moveStartTimeForwards(todayEvent.get('startDate8601'))},
                'end': {"dateTime": moveEndTimeBackwards(todayEvent.get('endDate8601'))}
            }
        )
        request.execute()
    except:
        print("Failed to update event")


def calendarDelete(deletedEvent):
    service = accessGoogleCalendarAPIService()
    try:
        request = service.events().delete(
            calendarId='primary',
            eventId=calendarGetEventID(deletedEvent),
        )
        request.execute()
    except googleapiclient.errors.HttpError:
        print("Failed to delete event")


def youtubeGetEventID(targetEvent):
    youtube = accessYouTubeLiveStreamingAPIService()
    while True:
        events = youtube.liveBroadcasts().list(
            part="snippet,contentDetails,status",
            broadcastType="all",
            mine=True
        )
        for tempEvent in events['items']:
            if ((tempEvent['summary'] == targetEvent['title']) and (tempEvent['start']['dateTime'] == targetEvent['startDate8601']) and (tempEvent['end']['dateTime'] == targetEvent['endDate8601'])):
                return tempEvent['id']


def youtubeInsert(event):
    youtube = accessYouTubeLiveStreamingAPIService()
    # "part" identifies the properties that the write operation will set as well as the properties that the API response will include
    # The parameter values can be included are id, snippet, cdn, contentDetails and status
    # See https://developers.google.com/youtube/v3/live/docs/liveBroadcasts for more references
    request = youtube.liveBroadcasts().insert(
        part="snippet,contentDetails,status",
        body={
            "contentDetails": {
                "enableClosedCaptions": True,
                "enableContentEncryption": True,
                "enableDvr": True,
                "enableEmbed": True,
                "recordFromStart": True,
                "startWithSlate": True,
                "enableAutoStart": True,
                "enableAutoStop": True,
                "enableLowLatency": True
            },
            "snippet": {
                "title": event.get('title'),
                "description": event.get('description'),
                "scheduledStartTime": moveStartTimeForwards(event.get('startDate8601')),
                "scheduledEndTime": moveEndTimeBackwards(event.get('endDate8601'))
            },
            "status": {
                "privacyStatus": "public",
                "selfDeclaredMadeForKids": False
            }
        }
    )
    request.execute()


def youtubeUpdate(event):
    youtube = accessYouTubeLiveStreamingAPIService()
    request = youtube.liveBroadcasts().update(
        part="snippet",
        body={
            "snippet": {
                "title": event.get('title'),
                "description": event.get('description'),
                "scheduledStartTime": moveStartTimeForwards(event.get('startDate8601')),
                "scheduledEndTime": moveEndTimeBackwards(event.get('endDate8601'))
            },
            "id": youtubeGetEventID(event)
        }
    )
    request.execute()


def youtubeDelete(event):
    youtube = accessYouTubeLiveStreamingAPIService()
    request = youtube.liveBroadcasts().delete(
        id=youtubeGetEventID(event)
    )
    request.execute()


def compareEvent(todayEvents, existedEvents):
    flag = False  # if found the same event, then change to True
    if (len(existedEvents) == 0):
        for todayEvent in todayEvents:
            calendarInsert(todayEvent)
            youtubeInsert(todayEvent)
    else:
        for todayEvent in todayEvents:
            for existedEvent in existedEvents:
                # compare the same event (select by title)
                if (todayEvent["title"] == existedEvent["title"]):
                    if (todayEvent["startDate"] != existedEvent["startDate"] or todayEvent["endDate"] != existedEvent["endDate"]):
                        calendarUpdate(todayEvent, existedEvent)
                        youtubeUpdate(todayEvent, existedEvent)
                        flag = True
                        existedEvents.remove(existedEvent)
            if (flag == False):  # new event added
                calendarInsert(todayEvent)
                youtubeInsert(todayEvent)
            else:
                flag = False
        if (len(existedEvents) > 0):  # old event deleted
            for deletedEvent in existedEvents:
                calendarDelete(deletedEvent)
                youtubeDelete(deletedEvent)


def removePastEvent(existedEvents):
    for existedEvent in existedEvents:
        existedEventTime = existedEvent.get("endDate")[11:19]
        if (existedEventTime < datetime.now().strftime('%H:%M:%S')):
            existedEvents.remove(existedEvent)
            calendarDelete(existedEvent)
        else:
            break
    return existedEvents


def loadUnpassedTodayEventsFromWeb():
    # Load all events from Web
    jsonEvents = requests.get(
        'https://today.wisc.edu/events/feed/469.json').text
    events = json.loads(jsonEvents)

    # Select today's events in Auditorium from the web
    todayEvents = []
    i = 0
    # today's date in string format of "2021/08/16"
    today = date.today().strftime("%Y/%m/%d")
    for event in events:
        if (today in event['startDate']) and ('Auditorium' in event['location']):
            todayEvents.append(event)
            if (i == 10):
                break

    return todayEvents


def job(code):
    # update CredentialCode
    CredentialCode = code

    # get today's events
    todayEvents = loadUnpassedTodayEventsFromWeb()

    # today's date in string format of "2021-Aug-16"
    today = date.today().strftime("%Y-%b-%d")
    # file name for today's .json
    todayJsonFileName = 'Calendar-' + today + '.json'

    # check .json file:
    # if not existed, create today's .json and remove yesterday's .json
    # if existed, see if it needs update
    if os.path.exists(todayJsonFileName):
        # get existedEvents and remove those has passed
        #existedEvents = json.loads(open(jsonFileName, "r").read())
        # get existedEvents and remove those has passed
        existedEvents = removePastEvent(
            json.loads(open(todayJsonFileName, "r").read()))
        compareEvent(todayEvents, existedEvents)
    else:
        # yesterday's date in string
        yesterday = (date.today() - timedelta(days=1)).strftime("%Y-%b-%d")
        yesterdayJsonFileName = 'Calendar-' + yesterday + '.json'
        if (os.path.exists(yesterdayJsonFileName)):
            os.remove(yesterdayJsonFileName)
        for event in todayEvents:
            calendarInsert(event)
            youtubeInsert(event)

    # update .json (inserting new, deleting old / past)
    with open(todayJsonFileName, "w") as outfile:
        json.dump(todayEvents, outfile)


if __name__ == '__main__':
    schedule.every(20).minutes.do(
        job, "4/1AX4XfWjmLeCsEZKzxUdBdWtdWz7DmOTQrR18nSrVBfAknvXybpVqD7ZbV_Q")

    while 1:
        schedule.run_pending()
        time.sleep(1)
