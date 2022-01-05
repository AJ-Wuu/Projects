# YouTube Live Streaming Integration Manager
## --> Auto-Scheduler for Multiple Live Streaming on YouTube  
Preparation for this project:
1. Get the credential file from https://console.cloud.google.com/apis -> Credentials -> Download OAuth Client
2. Prepare your own python code for generating your events into JSON file (with title, description, startDate8601, endDate8601)
3. Go to https://accounts.google.com/o/oauth2/xxx to get the initial code, paste it in the parameter of schedule()
4. Add the calendar synchronization to HELO: Google Calendar -> Setting -> Settings for my calendars
   * Access permisions for events -> Make available to public
   * Integrate calendar -> Public address in iCal format
5. Connect the HELO to YouTube account (add Stream Key and Stream URL)
6. (Deprecated) Delete previous .pickle when resume the program
