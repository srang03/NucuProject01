# NucuProject01
NucuProject01
### NUCU SUPPORT & FEEDBACK APPLICATION
#### Project with Nucu using Kotlin
#### enterprise domain : https://nucu.fi/

### Introduction
This project is part of our student exchange in Finland. Three of us, Youngjoon Yang(Product Owner), Sungwoo An(Scrum Master), and Seonwoo Kim(Developer), worked as a team to develop a application. Our technical goal is to make a smart phone application that helps Nucu customers to put some significiant feedbacks and support easily so that the company can synthesize the data easily. Also for the customers, they can access with their own number that can identify each other and contact Nucu by email or phone. Therefor the users can search their own personal information on this application and contact Nucu efficiently. Our learning goal is twofold. We have never used Kotlin language before, so we aim to learn new language and technical skills to create a useful application. On the other hand, we are unusual with scrum & agile system because students in my home country don’t use it much. We aim to learn some project working skills and get used to this system. 


### Project Setting ans Scope
| Category | Setting Result |
|:---:|:---:|
|Project Name|Nucu Mobile Service|
|Target Device|Android Smart Phone|
|Minimum SDK|API Level 21|
|Database|SQLight|
|Customer Information|Apptivo API|
|Network Communication|OKHttp|


### Project Data Flow
![image](https://user-images.githubusercontent.com/53038387/82720949-4bbec180-9cf3-11ea-991f-84f355ef6fb1.png)



### Technical part of the project
  1. Project Plan
  We had meeting with Nucu company every week and after the meeting we had our own team meeting reviewing about next weekly plan. We prepared backlog to check and review our plan. At first we separate each task by our roll, but we realized that we had only one developer since our teammates are 3 people. So we decided to support developer’s task together so that we can proceed the project smoothly. 
 We had to concentrate on API Apptivo, Google form and QR Code to use in the application activities (intro, start, main) and fragments (home, support, feedback, setting). We planned to work on each activities and fragments for one week. 
  
  2. IntroActivity
    1. Call handler method and execute runnable method after certain amount of time
    2. Use onResume() and onPause() method to show the activity
    
  3. StartActivity
    1. Use room library to save local database
    ```c
            var db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-name"
        ).allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    ```

    2. First time using the application
      Create the content banner and let the user put their customerNumber
    
    
