# Auto-Scheduler for multiple broadcasts for YouTube Auto Live Streaming.  
Preparation for this project:
1. Get the credential file from https://console.cloud.google.com/apis -> Credentials -> Download OAuth Client
2. Go to https://accounts.google.com/o/oauth2/auth?response_type=code&client_id=330008079846-2q81f3b9t5944jqfhrbiaihkv33gltcs.apps.googleusercontent.com&redirect_uri=urn%3Aietf%3Awg%3Aoauth%3A2.0%3Aoob&scope=https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fyoutube&state=K4nH8YUMZAYOqYJwmzg5jUfP0GQnUH&access_type=offline&prompt=consent&include_granted_scopes=true
   to get the initial code, paste it in the parameter of schedule()
3. Add the calendar synchronization to HELO
   (Google Calendar -> Setting -> Settings for my calendars -> Access permisions for events -> Make available to public
                                                            -> Integrate calendar -> Public address in iCal format)
4. Connect the HELO to YouTube account (add Stream Key and Stream URL)
5. Delete previous .pickle when resume the program
