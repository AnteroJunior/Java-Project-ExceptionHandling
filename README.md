## Code Smells
### 1. Long Method
  1.1. Community.java<br>
    - showFeed();<br>
    - seeRequest();<br>
    - seeCommunityInbox();<br>
  1.2. User.java<br>
    - showMyFeed();<br>
    - seeFriendRequest();<br>
    - changeFeedVisibility();<br>
    - deleteAccount(); <br>
    
### 2. Large class<br>
  2.1. Iface.java<br>
  2.2. User.java<br>
 
### 3. Switch Statements<br>
  3.1. Iface.java<br>
    -> Menu in main();<br>
  3.2. User.java<br>
    -> changeFeedVisibility();<br>
    -> seeFriendRequest();<br>
    -> showMyFeed();<br>

### 4. Duplicated code<br>
  4.1. Iface.java<br>
    -> searchUser(); and searchCommunity();<br>
    -> While sending message or post to a user or to a group;<br>
    
    ```
    String group_name = console.readLine("Group name: ");
      Community group = searchCommunity(group_name, groups);
      if(group != null){
          String text = console.readLine("Insert your message: ");
          Inbox new_message = new Inbox(text, loggedUser);
          group.sendMessage(new_message);
          System.out.println("Message sent successfully.");
      } else {
          throw new Exception("Group not found.");
      }
      ```
