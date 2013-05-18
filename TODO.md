SOEN 6441/1 Collaborative Email Client
======================================

Project I Email Basics
----------------------
* GUI

* Compose New Email
* Save Draft Email
* Display Your inbox folder hiearchy
* Create Sub folders
* Move mail to sub folders
* Have a send mail functionality that moves email to a Sent and ToSend folder
* All functionalites should have menu, right mouse click and hot keys
* delete an email from folders
* delete folders except top level inbox

* Persistent Storage

###Initial Startup Of Client:

* Prompt user for mailbox id
    * If Mailbox exists continue
    * If Mailbox doesn't exist create it with empty top level dirctories:

        - Inbox
        - Trash
        - Outbox
        - Sent
        - Drafts
-Load Top level Directories from Persistent Storage
    - Read subfolders in Mailbox
- Display GUI
    - Tree explorer of top level folder displayed
    - Inbox Selected
    - Email list updated
        - Load emails from FileSystem
        - Iterate through Email headers and populate Email List
        - Select First Email in List
        - Update EmailContent Pane
            - Load Email Content for Selected Email
            - Display it
        
####Compose New mail
####Reply to email
####Create New Folder
####Delete Folder
####Move Folder
####Delete Email
####Reply to Email
####Move Email
####Handle Keyboard Quick keys
####Empty Trash



Project II Email Management
---------------------------
* Email Templates
* Email Search display only email in your inbox that meet a filter criteria
* Email filters that move emails into a specification inbox folder that match search criteria periodically
* Create a new Meeting with other email attendees create email informing them
* Edit an Meeting
* Delete a Meeting

Project III Collaboration
-------------------------
* Send email through network between to email clients at least 3 clients your filters should apply to received email
* When you create a new meeting involving another person they should recevie email and be able to accept or decline the appointment and edit over the network
* Allow two instances of your program to concurrently edit an appointment over a network using optismistic locking
