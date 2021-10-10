import pandas as pd
from bs4 import BeautifulSoup
from selenium import webdriver
import re

#Preparation
#browser driver, note that the version of chrome and chromedriver should match
driver = webdriver.Chrome(executable_path='C:/Program Files/Google/Chrome/Application/chromedriver.exe')
driver.get('https://www.biotech.wisc.edu/EventCalendar/calendar.asp?LoginGUID=&viewspan=month&uwbceventsonly=False&selectlocationid=&SelectEventMonth=6&SelectEventYear=2022')
content = driver.page_source
soup1 = BeautifulSoup(content, features="html.parser") #used for extracting data
    
#Get Data
Links = [] #store every hyperlink in the calendar
for element in soup1('a'):
    Links.append(element.get('href'))
        
Calendars = [] #store every link of events (abandon links for every day)
for Link in Links:
    if Link.__contains__('eventschedule'): #search for key word
        Calendars.append('https://www.biotech.wisc.edu/EventCalendar/' + Link) #the raw link does not contain the prefix

Titles = []
Locations = []
Dates = []
Times = []
#Infos = [] #not necessary for now
for Calendar in Calendars:
    driver.get(Calendar)
    content = driver.page_source
    soup2 = BeautifulSoup(content, features="html.parser") #change to a new soup
    tags = soup2('div') + soup2('span') #get both elements in 'div' and 'span'

    Texts = [] #store all text information (abandon those written in HTML)
    for tag in tags:
        Texts.append(tag.contents[0])

    for Text in Texts:
        if Text.__contains__('\n            '):
            temp = Text
            temp = temp.replace('\n            ', '') #remove the unnecassary parts
            temp = temp.replace('\n        ', '')
            Titles.append(temp)
        if ('UWBC Conference Room' in Text or 'UWBC Auditorium' in Text or 'UWBC Atrium' in Text):
            if ('UWBC Conference Room' in Text):
                Locations.append(Text.replace('                                                                                                                                                                               \n\t    ', ''))
            if ('UWBC Auditorium' in Text):
                Locations.append(Text.replace('                                                                                                                                                                                         \n\t    ', ''))
            if ('UWBC Atrium' in Text):
                Locations.append(Text.replace('                                                                                                                                                                              \n\t    ',''))
        if ('January' in Text or 'February' in Text or 'March' in Text or 'April' in Text or 'May' in Text or 'June' in Text or 'July' in Text or 'August' in Text or 'September' in Text or 'October' in Text or 'November' in Text or 'December' in Text):
            Dates.append(Text.replace('\n      ', ''))
        if ('AM' in Text or 'PM' in Text):
            Times.append(Text.replace('\n      ', ''))
        #if (('@' in Text or '-' in Text) and ('Nite' not in Text and 'AM' not in Text and 'PM' not in Text and '\n            ' not in Text)):
        #    Infos.append(Text)

#Output   
#write all data to .csv file with the title of each column
df = pd.DataFrame({'Titles': Titles, 'Locations': Locations, 'Dates': Dates, 'Times': Times})
df.to_csv('Schedular.csv', index=False, encoding='utf-8')

driver.quit() #remember to close after finished
