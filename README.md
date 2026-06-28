# My Personal Project is Personal Reading Tracker 

## A Java Applicattion for Managing Reading 

This application will help users to keep track of the books they want to read, they have read and are currently reading. Users will be able to add books in the reading list with details such as author, title, genre and reading status.

This project is for **book lovers** who want to organize their reading and see their reading progress.

I am interested in this project as I enjoy reading a lot and liked the idea of building something personal.

## User Stories 
- As a user, I want to be able to add a book to my reading list
- As a user, I want to be able to view a list of all books in my reading list
- As a user, I want to be able to change reading status of a boook
- As a user, I want to be able to view the books with specific status
- As a user, I want to be given the option to save my reading list to a file 
- As as user, I want to be given the option to load my reading list from a file

## Instructions for End User
- You can view the panel that displays books that have already been added to the reading list in the main area
  of the GUI. Each added book will appear in this panel immediately after it is added.
- You can **add a book** by typing the book title and author into the text fields and clicking **Add Book** button.
- You can update the reading status of an existing book by typing the book title and new status and 
  then click **Set Status** button.
- You can locate my visual component by looking at the book image displayed at the top of the window.
  This image appears whenever a book is added to the reading list.
- You can save the state of my application by clicking the **Save**button.
- You can reload the state of my application by clicking the **Load** button.

## Phase 4: Task 2
Thu Mar 26 10:09:51 PDT 2026
Book test book added to reading list.
Thu Mar 26 10:10:08 PDT 2026
Book test book2 added to reading list.
Thu Mar 26 10:10:23 PDT 2026
Status of book test book updated to Finished.

## Phase 4: Task 3
I would have considered spending more time improving the separation between the GUI and core logic. 
Currently, there is high coupling among classes like ReadingListGUI, ReadingList, JsonReader and JsonWrtier.
This is making system harder to maintain and extend. To address this, I would introduce another class to act as an intermediary between the GUI and the model/persistence components. This would allow the GUI to focus purely on presentation and user interaction, while the new class handles application logic and making the system easier to test.
Additionally, this refactoring would reduce direct dependencies between classes and make future changes—such as modifying how data is stored or adding new features—easier to implement.







  