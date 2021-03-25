package fr.iut.appmob.fortuna.data;

import android.content.Context;
import android.content.SharedPreferences;

import java.time.LocalTime;

public class DataManagement {

    private static final String CONFIG = "CONFIG";
    private static final String MONEY = "MONEY" ;
    private static final String CHART = "CHART";

    public static boolean isFirstRun(Context context) {
        return context.getSharedPreferences("CONFIG", Context.MODE_PRIVATE)
                .getBoolean("isFirstRun", true);

    }

    public static void setFirstAndLastName(Context context, String firstname, String lastname) {
        SharedPreferences config = context.getSharedPreferences(CONFIG, context.MODE_PRIVATE);

        config.edit().putString("first_name", firstname).commit();
        config.edit().putString("last_name", lastname).commit();

    }

    public static void setProfilePicture(Context context, int iconID) {
        context.getSharedPreferences(CONFIG, Context.MODE_PRIVATE).edit()
                .putInt("icon", iconID)
                .commit();

    }

    public static void setFirstRun(Context context, boolean value) {
        context.getSharedPreferences(CONFIG, Context.MODE_PRIVATE).edit()
                .putBoolean("isFirstRun", value).commit();

    }

    private static final String BALANCE = "balance";
    public static void setBalance(Context context, String balance) {
        context.getSharedPreferences(MONEY, context.MODE_PRIVATE).edit()
                .putFloat(BALANCE, Float.parseFloat(balance))
                .commit();

        context.getSharedPreferences(CHART, context.MODE_PRIVATE).edit()
                .putFloat(LocalTime.now().toString(), Float.parseFloat(balance))
                .commit();

    }

    private static final String DEPOSIT = "deposit";
    public static void addNewDeposit(Context context, String depositValue) {
        float income = getIncome(context);
        float newDeposit = Float.parseFloat(depositValue);
        float total = income + newDeposit;

        float balance = getBalance(context);
        float newBalance = balance + newDeposit;

        SharedPreferences money = context.getSharedPreferences(MONEY, context.MODE_PRIVATE);
        SharedPreferences chart = context.getSharedPreferences(CHART, context.MODE_PRIVATE);

        money.edit().putFloat(DEPOSIT, total).commit();
        chart.edit().putFloat(LocalTime.now().toString(), newBalance).commit();
        money.edit().putFloat(BALANCE, newBalance).commit();

    }


    private static final String EXPENSE = "expense";
    public static void addNewExpense(Context context, String expenseValue, String category) {
        float expenseTotalCategory = getExpenseOfCategory(context, category.toLowerCase());
        float newExpense = Float.parseFloat(expenseValue);
        float newTotalCategory = expenseTotalCategory + newExpense;

        float expenseTotal = getExpense(context);
        float newExpenseTotal = expenseTotal + newExpense;

        float balance = getBalance(context);
        float newBalance = balance - newExpense;

        String dbName = EXPENSE + category;

        SharedPreferences money = context.getSharedPreferences(MONEY, context.MODE_PRIVATE);
        SharedPreferences chart = context.getSharedPreferences(CHART, context.MODE_PRIVATE);

        money.edit().putFloat(EXPENSE, newExpenseTotal).commit();
        money.edit().putFloat(BALANCE, newBalance).commit();
        chart.edit().putFloat(LocalTime.now().toString(), newBalance).commit();
        money.edit().putFloat(dbName, newTotalCategory).commit();

    }

    // GETTER FOR THE BALANCE / INCOME / EXPENSE - HOME FRAGMENT
    public static float getIncome(Context context) {
        return context.getSharedPreferences(MONEY, context.MODE_PRIVATE)
                .getFloat(DEPOSIT, 0);

    }

    public static float getExpenseOfCategory(Context context, String category) {
        return context.getSharedPreferences(MONEY, context.MODE_PRIVATE)
                .getFloat(EXPENSE + category, 0);

    }

    public static float getBalance(Context context) {
        return context.getSharedPreferences(MONEY, context.MODE_PRIVATE)
                .getFloat(BALANCE, 0);

    }

    private static float getExpense(Context context) {
        return context.getSharedPreferences(MONEY, context.MODE_PRIVATE)
                .getFloat(EXPENSE, 0);
    }

    // Get the progress bar
    private final static int PERCENT = 100;
    public static int getProgress(Context context, String category) {
        float value = context.getSharedPreferences(MONEY, context.MODE_PRIVATE)
                .getFloat(EXPENSE + category.toLowerCase(), 0);

        return (int) ((value * PERCENT) / (getExpense(context)));
    }

    private DataManagement() {}

}
