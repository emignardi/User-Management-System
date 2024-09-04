package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.User;

public class UserDao {
	
	private String url = "jdbc:mysql://localhost:3306/user_management";
	private String user = "root";
	private String password = "password";
	
	protected Connection connect() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, user, password);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	public void createUser(User user) throws SQLException {
		try (Connection connection = connect();
				PreparedStatement statement = connection.prepareStatement("INSERT INTO users (name, email, country) VALUES (?,?,?);");) {
			statement.setString(1, user.getName());
			statement.setString(2, user.getEmail());
			statement.setString(3, user.getCountry());
			statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public User readUser(int id) {
		User user = null;
		try (Connection connection = connect();
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE id = ?");) {
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String name = rs.getString("name");
				String email = rs.getString("email");
				String country = rs.getString("country");
				user = new User(id, name, email, country);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
	
	public List<User> readAllUsers() {
		List<User> users = new ArrayList<>();
		try (Connection connection = connect();
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM users");) {
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String country = rs.getString("country");
				users.add(new User(id, name, email, country));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return users;
	}
	
	public void updateUser(User user) throws SQLException {
		try (Connection connection = connect();
				PreparedStatement statement = connection.prepareStatement("UPDATE users SET name = ?, email = ?, country = ? WHERE id = ?;");) {
			statement.setString(1, user.getName());
			statement.setString(2, user.getEmail());
			statement.setString(3, user.getCountry());
			statement.setInt(4, user.getId());
			statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteUser(int id) throws SQLException {
		try (Connection connection = connect();
				PreparedStatement statement = connection.prepareStatement("DELETE FROM users WHERE id = ?;");) {
			statement.setInt(1, id);
			statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
