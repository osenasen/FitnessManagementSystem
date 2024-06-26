package fms.utils;

import fms.model.RecipeModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientNutritionDAO {
    public void addRecipeToClient(int clientId, int recipeId) {
        if (!isRecipeLinkedToClient(clientId, recipeId)) {
            String sql = "INSERT INTO Client_Nutrition(client_id, recipe_id) VALUES(?, ?)";
            try (Connection conn = DatabaseManager.connect();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, clientId);
                pstmt.setInt(2, recipeId);
                pstmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Recipe already linked to client.");
        }
    }
    
    public void removeRecipeFromClient(int clientId, int recipeId) {
        String sql = "DELETE FROM Client_Nutrition WHERE client_id = ? AND recipe_id = ?";
        try (Connection conn = DatabaseManager.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, clientId);
            pstmt.setInt(2, recipeId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    private boolean isRecipeLinkedToClient(int clientId, int recipeId) {
        String sql = "SELECT COUNT(*) FROM Client_Nutrition WHERE client_id = ? AND recipe_id = ?";
        try (Connection conn = DatabaseManager.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, clientId);
            pstmt.setInt(2, recipeId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
    
    public List<RecipeModel> getRecipesForClient(int clientId) {
        String sql = "SELECT r.* FROM Recipe r JOIN Client_Nutrition cn ON r.id = cn.recipe_id WHERE cn.client_id = ?";
        List<RecipeModel> recipes = new ArrayList<>();
        try (Connection conn = DatabaseManager.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, clientId);
            ResultSet rs = pstmt.executeQuery();
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
            System.out.println(e.getMessage());
        }
        return recipes;
    }
}
