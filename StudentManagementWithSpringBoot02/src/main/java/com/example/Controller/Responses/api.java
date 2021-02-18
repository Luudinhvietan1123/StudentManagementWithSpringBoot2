package com.example.Controller.Responses;

import com.example.Model.Entities.SqlServerConnection;
import com.example.Model.Entities.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "prefix")
public class api {

    @PostMapping(value = "/user")
    public void insertUser(@RequestBody UserInserted userInserted){
        String sql = "insert into user (id, username,password, fullname, email, address, phone) value ('"
                + userInserted.getId() + "','"
                + userInserted.getUsername() + "','"
                + userInserted.getPassword() + "','"
                + userInserted.getFullname() + "','"
                + userInserted.getEmail() + "','"
                + userInserted.getAddress() + "','"
                + userInserted.getPhone() + "')";
        try{
            Statement statement = SqlServerConnection.getInstanceConnection().createStatement();
            statement.execute(sql);
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try{
                SqlServerConnection.getInstanceConnection().close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public void insertUser1(@RequestBody UserInserted userInserted){
        String sql = "insert into user (id, username,password, fullname, email, address, phone) value ('"
                + userInserted.getId() + "','"
                + userInserted.getUsername() + "','"
                + userInserted.getPassword() + "','"
                + userInserted.getFullname() + "','"
                + userInserted.getEmail() + "','"
                + userInserted.getAddress() + "','"
                + userInserted.getPhone() + "')";
        try{
            Statement statement = SqlServerConnection.getInstanceConnection().createStatement();
            statement.execute(sql);
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try{
                SqlServerConnection.getInstanceConnection().close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @GetMapping(value = "/user/list")
    public Object getUserList() {
        String sql = "select * from user order by fullname ASC";
        List<User> userList = new ArrayList<>();
        try {
            Statement statement = SqlServerConnection.getInstanceConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                User user = new User(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getInt(7)
                );
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                SqlServerConnection.getInstanceConnection().close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return userList;
        }
    }

    @RequestMapping(value = "/user/list", method = RequestMethod.GET)
    public Object getUserList1(){
        String sql = "select * from user order by fullname ASC";
        List<User> userList = new ArrayList<>();
        try{
            Statement statement =SqlServerConnection.getInstanceConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                User user = new User(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getInt(7)
                );
                userList.add(user);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                SqlServerConnection.getInstanceConnection().close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return userList;
        }
    }

    @PutMapping(value = "/user/{user_id}")
    public void updateUser(@PathVariable(name="user_id") int userID,@RequestBody UserInserted userInserted)
    {
        String findUser = "SELECT id FROM USER WHERE id = '" + userID + "';";
        try
        {
            Statement statement = SqlServerConnection.getInstanceConnection().createStatement();
            ResultSet verifyUser = statement.executeQuery(findUser);
            if(verifyUser.next())
            {
                String update= "UPDATE USER SET id = " + userID;
                if(userInserted.getUsername()!=null)
                {
                    update+=",username = '" +userInserted.getUsername() + "'";
                }
                if(userInserted.getPassword()!=null)
                {
                    update+=",password = '" +userInserted.getPassword() + "'";
                }
                if(userInserted.getFullname()!=null)
                {
                    update+=",fullname = '" +userInserted.getFullname() + "'";
                }
                if(userInserted.getEmail()!=null)
                {
                    update+=",email = '" +userInserted.getEmail() + "'";
                }
                if(userInserted.getAddress()!=null)
                {
                    update+=",address = '" +userInserted.getAddress() + "'";
                }
                update+=" WHERE id = " + userID + ";";
                statement = SqlServerConnection.getInstanceConnection().createStatement();
                statement.executeUpdate(update);
                System.out.println("UPDATE SUCCESSFULLY");
            }
            else
            {
                System.out.println("THIS ID IS NOT EXIST");
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            System.out.println("Update fail");
        }
    }

    @DeleteMapping(value = "/user")
    public void deleteUser(@RequestParam(name = "user_id") int userID)
    {
        String delete = "DELETE FROM USER WHERE id = " + userID + ";";
        //     System.out.println(delete);
        try{
            Statement statement = SqlServerConnection.getInstanceConnection().createStatement();
            statement.execute(delete);
            System.out.println("Delete successfully");
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            System.out.println("Delete fail");
        }finally {
            try{
                SqlServerConnection.getInstanceConnection().close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

}

@Getter
@Setter
class UserInserted {
    private int id, phone;
    private String username, password, fullname, email, address;
}
