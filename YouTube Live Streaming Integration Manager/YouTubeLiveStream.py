'''@author AJWuu'''

# Run youtube.liveStreams.insert
# Document: https://developers.google.com/youtube/v3/live/docs/liveStreams/insert

from datetime import date, timedelta
import os
import requests
import json
import google_auth_oauthlib.flow
import googleapiclient.discovery
import googleapiclient.errors

def getCalendar():
    # Load all events from Web
    jsonEvents = requests.get(
        'https://today.wisc.edu/events/feed/469.json').text
    events = json.loads(jsonEvents)

    # Select today's events in Auditorium
    todayEvents = []
    i = 0
    today = date.today().strftime("%Y/%m/%d") # today's date in string format of "2021/08/16"
    for event in events:
          if (today in event['startDate']) and ('Auditorium' in event['location']):
                todayEvents.append(event)
                i += 1
                if (i == 10):
                      break

    # TODAY's events
    # If not existed, create today's .json and remove yesterday's .json
    # If existed, see if it needs update
    today = date.today().strftime("%Y-%b-%d") # today's date in string format of "2021-Aug-16"
    if os.path.exists('Calendar-' + today + '.json'):
          print("Exists")
    else:
          yesterday = (date.today() - timedelta(days=1)).strftime("%Y-%b-%d") # yesterday's date in string
          os.remove('Calendar-' + yesterday + '.json')
          with open('Calendar-' + today + '.json', "w") as outfile:
            json.dump(todayEvents, outfile)
                      
                

    # Select events in Auditorium
    print("Test")


def youtubeUpload():
    scopes = ["https://www.googleapis.com/auth/youtube.force-ssl"]
    # scopes has to contain at least one of the following:
    # https://www.googleapis.com/auth/youtube
    # https://www.googleapis.com/auth/youtube.force-ssl

    # Disable OAuthlib's HTTPS verification when running locally.
    # *DO NOT* leave this option enabled in production.
    os.environ["OAUTHLIB_INSECURE_TRANSPORT"] = "1"

    api_service_name = "youtube"
    api_version = "v3"
    client_secrets_file = "my_secrets_file_OAuth_param.json"

    # Get credentials and create an API client
    flow = google_auth_oauthlib.flow.InstalledAppFlow.from_client_secrets_file(
        client_secrets_file, scopes)
    credentials = flow.run_console()
    youtube = googleapiclient.discovery.build(
        api_service_name, api_version, credentials=credentials)

    # "part" identifies the properties that the write operation will set as well as the properties that the API response will include
    # The parameter values can be included are id, snippet, cdn, contentDetails and status
    request = youtube.liveStreams().insert(
        part="snippet,cdn,contentDetails,status",
        body={
            "snippet": {
                "title": "YouTube Live Test",
                "description": "This is an optional field. I'm writing here to rememeber this could be a choice."
            },
            "cdn": {
                "frameRate": "60fps",
                "ingestionType": "rtmp",
                "resolution": "1080p"
            },
            "contentDetails": {
                "isReusable": True
            },
            "status": {
                # We could stop the live by stopping the encoder, and the YouTube live will automatically shut down after a few minutes
            }
        }
        # onBehalfOfContentOwner=Content_Owner_ID
    )
    response = request.execute()

    print(response)


def main():
    getCalendar()


if __name__ == "__main__":
    main()
