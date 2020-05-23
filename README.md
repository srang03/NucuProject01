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
###### Use room library to save local database
```       
            var db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-name"
          ).allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
```
          
          
###### First time using the application
```c
      
              val pagerAdapter = PagerAdapter(supportFragmentManager)
              val pager: ViewPager = findViewById<ViewPager>(R.id.viewPager)
              pager.adapter = pagerAdapter
```
          
          
###### Already used the application before
```k
            if(db.personDao().getAll().isNotEmpty()) {
              Customer.customerId = db.personDao().getAll().get(0).customerId
              Customer.customerName = db.personDao().getAll().get(0).customerName.toString()
              Customer.address = db.personDao().getAll().get(0).address.toString()
              val intent = Intent(applicationContext, MainActivity::class.java)
              startActivity(intent)
            }
```   
          
          
###### Check the exception of  customerNumber text
             * Wrong(blank or wrong range of number) -> Toast message   
             * Correct -> Use OkHttp library to bring user’s information from Apptivo API
             
             * To use customer’s information in Andriod Studio, use object and array to change the form into GSON type
             
```             
                  override fun onResponse(call: Call, response: Response) {

                                        val body = response?.body?.string()

                                        val gson = GsonBuilder().create()
                                        val jObject = JSONObject(body)
                                        val jArray = jObject.getJSONArray("data")

                                        val obj = jArray.getJSONObject(0)
                                        Customer.customerId= obj.getInt("customerId")
                                        Customer.customerName = obj.getString("customerName")
                                        val address = obj.getJSONArray("addresses")

                                        val AObject = address.getJSONObject(0)
                                        val addressCity = AObject.getString("city")
                                        val addressLine1 = AObject.getString("addressLine1")

                                        val addressPost = AObject.getString("zipCode")
                                        Customer.address = "${addressPost}, ${addressLine1}, ${addressCity}"

                                        Log.d("Tag", "${Customer.customerId}")
                                        Log.d("Tag", "${Customer.customerName}")
                                        Log.d("Tag", "${Customer.address}")

          
      
```

###### Execute thread communication from Android system by using handler and looper
```

          val handler = Handler(Looper.getMainLooper())

          val mDialogView = LayoutInflater.from(this@StartActivity).inflate(R.layout.alert_popup, null)
          val mBuilder = AlertDialog.Builder(this@StartActivity)
                         .setView(mDialogView)
                         .setTitle("Check your Customer Info")

          handler.post {
                  val mAlertDialog = mBuilder.show()
                  mDialogView.centerTextView.setText("Your name : ${Customer.customerName} \n Your Customer
                  number : ${customerNumber}")
                  mDialogView.saveButton.setOnClickListener {

                       mAlertDialog.dismiss()

                       db.personDao().insert(
                       Person(
                          Customer.customerId,
                          Customer.customerName,
                          Customer.address
                          )
                      )
```
###### Execute DialogView and AlertDialog
    * Let the user check whether their information is correct or not before using the application
        * save
            The information is being saved into the database
            Execute mAlertDialog.dismiss() method
        * cancel
            Execute mAlertDialog.dismiss() method
      
      
4. MainActivity  
  Make 4 fragments and convert each fragment by using navigation button 
```
        val bottomNavigation: BottomNavigationView = btm_nav

        homeFragment = HomeFragment()

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frame_layout, homeFragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()
```

5. HomeFragment
6. SupportFragment
7. FeedbackFragment
8. SettingView

### Conclusion
        
 
    
