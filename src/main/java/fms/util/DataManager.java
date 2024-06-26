package fms.util;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import fms.model.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataManager {
    private static final String USER_FILE = "src/main/resources/json/user_data.json";
    private static final String CLIENT_FILE = "src/main/resources/json/client_data.json";
    private static final String RECIPE_FILE = "src/main/resources/json/recipe_data.json";
    private static final String EXERCISE_FILE = "src/main/resources/json/exercise_data.json";
    
    private static final ObjectMapper mapper = new ObjectMapper();
    
    static {
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
    }
    
    public static UserModel loadUser() {
        try {
            return mapper.readValue(new File(USER_FILE), UserModel.class);
        } catch (IOException e) {
            e.printStackTrace();
            return new UserModel("defaultUser", "password");
        }
    }
    
    public static void saveUser(UserModel user) {
        try {
            mapper.writeValue(new File(USER_FILE), user);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static List<ClientModel> loadClients() {
        try {
            return mapper.readValue(new File(CLIENT_FILE), new TypeReference<List<ClientModel>>() {});
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
    
    public static void saveClients(List<ClientModel> clients) {
        try {
            mapper.writeValue(new File(CLIENT_FILE), clients);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static List<RecipeModel> loadRecipes() {
        try {
            return mapper.readValue(new File(RECIPE_FILE), new TypeReference<List<RecipeModel>>() {});
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
    
    public static void saveRecipes(List<RecipeModel> recipes) {
        try {
            mapper.writeValue(new File(RECIPE_FILE), recipes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static List<ExerciseModel> loadExercises() {
        try {
            return mapper.readValue(new File(EXERCISE_FILE), new TypeReference<List<ExerciseModel>>() {});
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
    
    public static void saveExercises(List<ExerciseModel> exercises) {
        try {
            mapper.writeValue(new File(EXERCISE_FILE), exercises);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static List<Integer> getClientRecipeIds(int clientId) {
        List<ClientModel> clients = loadClients();
        return clients.stream()
                   .filter(c -> c.getId() == clientId)
                   .findFirst()
                   .map(ClientModel::getRecipeIds)
                   .orElse(new ArrayList<>());
    }
    
    public static void updateClientRecipes(int clientId, List<Integer> recipeIds) {
        List<ClientModel> clients = loadClients();
        clients.stream()
            .filter(c -> c.getId() == clientId)
            .findFirst()
            .ifPresent(client -> {
                client.setRecipeIds(recipeIds);
                saveClients(clients);
            });
    }
}