package fr.iut.appmob.fortuna;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.EditText;

public class DataManagement {

    private static final String PREFERENCE_MONEY = "MONEY" ;

    private DataManagement() {}

    public static void setBalance(Context context, String balance) {
        SharedPreferences sharedPref = context.getSharedPreferences(PREFERENCE_MONEY, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putFloat("balance", Float.parseFloat(balance));
        editor.apply();
    }

    public static void addNewDeposit(Context context, String depositValue) {
        float income = getIncome(context);
        float newDeposit = Float.parseFloat(depositValue);
        float total = income + newDeposit;

        float balance = getBalance(context);
        float newBalance = balance + newDeposit;

        SharedPreferences sharedPref = context.getSharedPreferences(PREFERENCE_MONEY, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putFloat("deposit", total);
        editor.putFloat("balance", newBalance);
        editor.apply();
    }

    public static void addNewExpense(Context context, String expenseValue, String catg) {
        float expensett = getExpense(context, catg);
        float newExpense = Float.parseFloat(expenseValue);
        float total = expensett + newExpense;

        float expense = getExpenseTt(context);
        float totalL = expense + newExpense;

        float balance = getBalance(context);
        float newBalance = balance - newExpense;

        String dbName = "expense" + catg;

        SharedPreferences sharedPref = context.getSharedPreferences(PREFERENCE_MONEY, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putFloat("expense", totalL);
        editor.putFloat(dbName, total);
        editor.putFloat("balance", newBalance);
        editor.apply();
    }

    public static float getIncome(Context context) {
        SharedPreferences sharedPref = context.getSharedPreferences(PREFERENCE_MONEY, context.MODE_PRIVATE);
        return sharedPref.getFloat("deposit", 0);
    }

    public static float getExpense(Context context, String catg) {
        SharedPreferences sharedPref = context.getSharedPreferences(PREFERENCE_MONEY, context.MODE_PRIVATE);
        String dbName = "expense" + catg;
        return sharedPref.getFloat(dbName, 0);
    }

    public static float getBalance(Context context) {
        SharedPreferences sharedPref = context.getSharedPreferences(PREFERENCE_MONEY, context.MODE_PRIVATE);
        return sharedPref.getFloat("balance", 0);
    }

    public static float getExpenseTt(Context context) {
        SharedPreferences sharedPref = context.getSharedPreferences(PREFERENCE_MONEY, context.MODE_PRIVATE);
        return sharedPref.getFloat("expense", 0);
    }

    public static float getProgressHome(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCE_MONEY, context.MODE_PRIVATE);
        float value = sharedPreferences.getFloat("expenseHome", 0);
        float progress = (value * 100) / (getExpenseTt(context));

        return progress;
    }

    public static float getProgressPhone(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCE_MONEY, context.MODE_PRIVATE);
        float value = sharedPreferences.getFloat("expensePhone", 0);
        float progress = (value * 100) / (getExpenseTt(context));

        return progress;
    }

    public static float getProgressCar(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCE_MONEY, context.MODE_PRIVATE);
        float value = sharedPreferences.getFloat("expenseCar", 0);
        float progress = (value * 100) / (getExpenseTt(context));

        return progress;
    }

    public static float getProgressHealth(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCE_MONEY, context.MODE_PRIVATE);
        float value = sharedPreferences.getFloat("expenseHealth", 0);
        float progress = (value * 100) / (getExpenseTt(context));

        return progress;
    }

    public static float getProgressFood(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCE_MONEY, context.MODE_PRIVATE);
        float value = sharedPreferences.getFloat("expenseFood", 0);
        float progress = (value * 100) / (getExpenseTt(context));

        return progress;
    }

    public static float getProgressOther(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCE_MONEY, context.MODE_PRIVATE);
        float value = sharedPreferences.getFloat("expenseOther", 0);
        float progress = (value * 100) / (getExpenseTt(context));

        return progress;
    }

}
