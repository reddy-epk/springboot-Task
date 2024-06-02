###Configured the below files

#Basic mySql query
-- Create the users table
CREATE TABLE users (
  user_id BINARY(16) PRIMARY KEY,
  full_name VARCHAR(255) NOT NULL,
  mob_num VARCHAR(20) NOT NULL,
  pan_num VARCHAR(20) NOT NULL,
  manager_id BINARY(16),
  created_at DATETIME,
  updated_at DATETIME,
  is_active BOOLEAN
);

-- Create the managers table
CREATE TABLE managers (
  manager_id BINARY(16) PRIMARY KEY,
  full_name VARCHAR(255) NOT NULL,
  mob_num VARCHAR(20) NOT NULL
);

### Design Files
<details>
  Implented files:
- `src/main/java/controller/UserController.java`
- `src/main/java/model/UserController.java`
- `src/main/java/controller/Manager.java`
- `src/main/java/controller/User.java`
- `src/main/java/repository/Manager.java`
- `src/main/java/repository/User.java`
- `src/main/java/Service/UserService.java`
- `src/main/java/Service/UserController.java`
  
</details>
