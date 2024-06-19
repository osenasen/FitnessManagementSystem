package fms.utils;

import fms.model.RecipeModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RecipeDAO {
    public void addRecipe(RecipeModel recipe) {
        String sql = "INSERT INTO Recipe(name, imagePath, proteins, carbs, calories, linkPlaceholder) VALUES(?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseManager.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, recipe.getName());
            pstmt.setString(2, recipe.getImagePath());
            pstmt.setInt(3, recipe.getProteins());
            pstmt.setInt(4, recipe.getCarbs());
            pstmt.setInt(5, recipe.getCalories());
            pstmt.setString(6, recipe.getLinkPlaceholder());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<RecipeModel> getAllRecipes() {
        String sql = "SELECT * FROM Recipe";
        List<RecipeModel> recipes = new ArrayList<>();
        try (Connection conn = DatabaseManager.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                RecipeModel recipe = new RecipeModel();
                recipe.setId(rs.getInt("id"));
                recipe.setName(rs.getString("name"));
                recipe.setImagePath(rs.getString("imagePath"));
                recipe.setProteins(rs.getInt("proteins"));
                recipe.setCarbs(rs.getInt("carbs"));
                recipe.setCalories(rs.getInt("calories"));
                recipe.setLinkPlaceholder(rs.getString("linkPlaceholder"));
                recipes.add(recipe);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recipes;
    }
    public void updateRecipeImagePath(int recipeId, String newImagePath) {
        String sql = "UPDATE Recipe SET imagePath = ? WHERE id = ?";
        try (Connection conn = DatabaseManager.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newImagePath);
            pstmt.setInt(2, recipeId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
