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
        float expense = getExpense(context);
        float newExpense = Float.parseFloat(expenseValue);
        float total = expense + newExpense;

        float balance = getBalance(context);
        float newBalance = balance - newExpense;

        SharedPreferences sharedPref = context.getSharedPreferences(PREFERENCE_MONEY, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putFloat("expense" + catg, total);
        editor.putFloat("balance", newBalance);
        editor.apply();
    }

    public static float getIncome(Context context) {
        SharedPreferences sharedPref = context.getSharedPreferences(PREFERENCE_MONEY, context.MODE_PRIVATE);
        return sharedPref.getFloat("deposit", 0);
    }

    public static float getExpense(Context context) {
        SharedPreferences sharedPref = context.getSharedPreferences(PREFERENCE_MONEY, context.MODE_PRIVATE);
        return sharedPref.getFloat("expense", 0);
    }

    public static float getBalance(Context context) {
        SharedPreferences sharedPref = context.getSharedPreferences(PREFERENCE_MONEY, context.MODE_PRIVATE);
        return sharedPref.getFloat("balance", 0);
    }

}
