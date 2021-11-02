'''@author AJWuu'''

from datetime import date, datetime, timedelta, time
import os
from google.auth import credentials
import requests
import json
import pickle
from googleapiclient.discovery import build
from google_auth_oauthlib.flow import InstalledAppFlow
from google.auth.transport.requests import Request
import google_auth_oauthlib.flow
import googleapiclient.discovery
import googleapiclient.errors

CREDENTIALS_FILE = "WednesdayNiteCredentials.json"

calendarToken = "calendarToken.pickle"
youtubeToken = "youtubeToken.pickle"


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
            code = '4/1AX4XfWh0hQCOvXKZ2FqKA5GsGuXvsZziK1RyadrEL2V5O7Qyw_7z6oOd9Bg'
            flow.fetch_token(code=code)
            creds = flow.credentials

        # Save the credentials for the next run
        with open(youtubeToken, 'wb') as token:
            pickle.dump(creds, token)

    youtube = googleapiclient.discovery.build(
        'youtube', 'v3', credentials=creds)
    return youtube


def moveStartTime15MinForward(start):
    startTime = (datetime.strptime(
        start[11:19], '%H:%M:%S') - timedelta(minutes=15)).strftime("%H:%M:%S")
    start = start[:11] + startTime + start[19:]
    return start


def moveEndTime15MinBackward(end):
    endTime = (datetime.strptime(
        end[11:19], '%H:%M:%S') + timedelta(minutes=15)).strftime("%H:%M:%S")
    end = end[:11] + endTime + end[19:]
    return end


def calendarUpdate(todayEvent, existedEvent):
    # update the event to tomorrow 9 AM IST
    service = accessGoogleCalendarAPIService()

    d = date.now().date()
    tomorrow = date(d.year, d.month, d.day, 9)+timedelta(days=1)
    start = tomorrow.isoformat()
    end = (tomorrow + timedelta(hours=2)).isoformat()

    event_result = service.events().update(
        calendarId='primary',
        eventId='<place your event ID here>',
        body={
            "summary": 'Updated Automating calendar',
            "description": 'This is a tutorial example of automating google calendar with python, updated time.',
            "start": {"dateTime": start, "timeZone": 'Asia/Kolkata'},
            "end": {"dateTime": end, "timeZone": 'Asia/Kolkata'},
        },
    ).execute()

    print("updated event")
    print("id: ", event_result['id'])
    print("summary: ", event_result['summary'])
    print("starts at: ", event_result['start']['dateTime'])
    print("ends at: ", event_result['end']['dateTime'])


def calendarInsert(todayEvent):
    # creates one hour event tomorrow 10 AM IST
    service = accessGoogleCalendarAPIService()
    service.events().insert(calendarId='primary',
                            body={
                                'summary': todayEvent.get('title'),
                                'start': {"dateTime": moveStartTime15MinForward(todayEvent.get('startDate8601'))},
                                'end': {"dateTime": moveEndTime15MinBackward(todayEvent.get('endDate8601'))},
                            }
                            ).execute()


def calendarDelete(deletedEvent):
    # Delete the event
    service = accessGoogleCalendarAPIService()
    try:
        service.events().delete(
            calendarId='primary',
            eventId='<place your event ID here>',
        ).execute()
    except googleapiclient.errors.HttpError:
        print("Failed to delete event")

    print("Event deleted")


def youtubeUpdate():
    print("Test")


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
                "scheduledStartTime": moveStartTime15MinForward(event.get('startDate8601')),
                "scheduledEndTime": moveEndTime15MinBackward(event.get('endDate8601'))
            },
            "status": {
                "privacyStatus": "private",
                "selfDeclaredMadeForKids": False
            }
        }
    )
    response = request.execute()


def youtubeDelete():
    print("Test")


def compareEvent(todayEvents, existedEvents):
    flag = False  # if found the same event, then change to True
    for todayEvent in todayEvents:
        for existedEvent in existedEvents:
            # compare the same event (select by title)
            if (todayEvent["title"] == existedEvent["title"]):
                if (todayEvent["startDate"] != existedEvent["startDate"] or todayEvent["endDate"] != existedEvent["endDate"]):
                    calendarUpdate(todayEvent, existedEvent)
                    youtubeUpdate()
                    flag = True
                    existedEvents.remove(existedEvent)
        if (flag == False):  # new event added
            calendarInsert(todayEvent)
            youtubeInsert()
        else:
            flag = False
    if (len(existedEvents) > 0):  # old event deleted
        for deletedEvent in existedEvents:
            calendarDelete(deletedEvent)
            youtubeDelete()


def removePastEvent(existedEvents):
    for existedEvent in existedEvents:
        existedEventTime = time.strptime(
            existedEvent.get("endDate")[11:19], '%H:%M:%S')
        if (existedEventTime < time.datetime.now().strftime('%H:%M:%S')):
            existedEvents.remove(existedEvent)
        else:
            break
    return existedEvent


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
            i += 1
            if (i == 10):
                break

    return todayEvents


def getCalendar():
    # get today's events
    todayEvents = loadUnpassedTodayEventsFromWeb()

    # today's date in string format of "2021-Aug-16"
    today = date.today().strftime("%Y-%b-%d")
    # file name for today's .json
    jsonFileName = 'Calendar-' + today + '.json'

    # check .json file:
    # if not existed, create today's .json and remove yesterday's .json
    # if existed, see if it needs update
    if os.path.exists(jsonFileName):
        # get existedEvents and remove those has passed
        #existedEvents = json.loads(open(jsonFileName, "r").read())
        # get existedEvents and remove those has passed
        existedEvents = removePastEvent(
            json.loads(open(jsonFileName, "r").read()))
        compareEvent(todayEvents, existedEvents)
    else:
        # yesterday's date in string
        yesterday = (date.today() - timedelta(days=1)).strftime("%Y-%b-%d")
        os.remove('Calendar-' + yesterday + '.json')
        for event in todayEvents:
            calendarInsert(event)

    # update .json (inserting new, deleting old / past)
    with open(jsonFileName, "w") as outfile:
        json.dump(todayEvents, outfile)


def main():
    getCalendar()


if __name__ == "__main__":
    main()
