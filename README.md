# Lesson Scheduler

A web-based lesson scheduler developed with Java Spring MVC, Maven, CSS, JavaScript, and HTML. This application is designed to manage lessons, student balances, and teacher availability in an efficient and user-friendly manner.

## Features

### Teacher Features:
- **Manage Students**: Create, edit, and view students, including their balance and lesson history.
- **Lesson Management**: Create, edit, and approve lessons. Select students from a list and automatically fill in lesson details.
- **Availability Management**: Set unavailable times for each teacher.
- **View Calendar**: A landscape-oriented calendar that displays all lessons. You can also approve or edit lessons directly from the calendar.
- **Lesson Payments**: Students’ balances are updated with a portion of their tuition fee after each lesson. Future updates will integrate payment systems for digital transactions (e.g., Stax API).

### Student Features:
- **View Lessons**: Students can only view their lessons and balance. They cannot see other students' lessons.
- **Request Lessons**: Students can request lessons from available teachers.

### Admin Features:
- **Manage Payments**: Future updates will include payment management features, including invoice tracking and integration with payment platforms like Stax API.

### Upcoming Features:
- **Email/Phone Reminders**: Notifications for students and teachers (planned for future updates).
- **Mobile App**: An Android app version of the platform for teachers with improved mobile functionality.
- **Calendar Improvements**: Lessons will be displayed with heights proportional to their duration for better visual clarity. 

### Other Features:
- **Google Drive Integration**: Song sheets can be uploaded and managed on Google Drive for efficiency.
- **Responsive Design**: The web app is mobile-friendly and supports both teacher and student views.

## Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/lesson-scheduler.git
### Install dependencies:    
      ```bash
      mvn clean install

### Run the application:
      ```bash
    mvn spring-boot:run

### Future Plans 
- Integrate payment systems for secure digital transactions.
- Implement email and SMS notifications for reminders.
- Create an Android app for teachers to manage their lessons and students more efficiently.
- Optimize the calendar for better user experience and flexibility.
