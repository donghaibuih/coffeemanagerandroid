package com.example.donghai.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class AccountDB extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "coffeemanager.db";
    //TALBE ACCOUNT
   public static final String ACCOUNT_TABLE_ACCOUNT = "account";
   public static final String ACCOUNT_COLUMN_ID = "id";
   public static final String ACCOUNT_COLUMN_NAME = "name";
   public static final String ACCOUNT_COLUMN_PASSWORDS = "pass";
  //TABLE EMPLOYEE
    public static final String EMPLOYEE_TABLE = "employee";
    public static final String EMPLOYEE_COLUMN_ID = "id";
    public static final String EMPLOYEE_COLUMN_NAME = "name";
    public static final String EMPLOYEE_COLUMN_ADRESS = "address";
    public static final String EMPLOYEE_COLUMN_PHONENUMBER = "phonenumber";
    public static final String EMPLOYEE_COLUMN_POSITION = "position";
    //TABLE PRODUCT
    public static final String PRODUCT_TABLE = "product";
    public static final String PRODUCT_COLUMN_ID = "id";
    public static final String PRODUCT_COLUMN_PRODUCTNAME = "name";
    public static final String PRODUCT_COLUMN_PRODUCTPRICE = "price";
    public static final String PRODUCT_COLUMN_PRODUCTIMAGE = "image";
    //TABLE BILL
    public static final String BILL_TABLE = "bill";
    public static final String BILL_COLUMN_ID = "id";
    public static final String BILL_COLUMN_DATE = "date";
    public static final String BILL_COLUMN_EMPLOYEE = "employee";
    public static final String BILL_COLUMN_TOTALPRICE = "totalprice";

    //TABLE BILL DETAIL
    public static final String BILLDETAIL_TABLE = "billdetail";
    public static final String BILLDETAIL_BILLID = "billid";
    public static final String BILLDETAIL_ID = "id";
    public static final String BILLDETAIL_NUMBER = "number";
    public static final String BILLDETAIL_PRICE = "price";
    public static final String BILLDETAIL_PRODUCTNAME = "productname";
















    public AccountDB(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sqlAccount = "create table account " +
                "(id integer , name text primary key,pass text)";
        String sqlEmployee = "create table employee " +
                "(id integer , name text, address text, phonenumber text primary key, position text)";
        String sqlProduct = "create table product " +  "(id integer , name  text primary key,price float,  image BLOB )";
        String sqlBill = "create table bill " +
                "(id text primary key, date text, employee text,totalprice float)";
        String sqlBillDetail = "create table billdetail " +
                "(id integer primary key, billid text,productname text, number integer, price float )";



        sqLiteDatabase.execSQL(sqlEmployee);
        sqLiteDatabase.execSQL(sqlAccount);
        sqLiteDatabase.execSQL(sqlProduct);
        sqLiteDatabase.execSQL(sqlBill);
        sqLiteDatabase.execSQL(sqlBillDetail);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("DROP TABLE IF EXISTS account");
        db.execSQL("DROP TABLE IF EXISTS employee");
        db.execSQL("DROP TABLE IF EXISTS product");
        db.execSQL("DROP TABLE IF EXISTS bill");
        db.execSQL("DROP TABLE IF EXISTS billdetail");
        onCreate(db);

    }


    public ArrayList<BillDetail> getBillDetailByBillID(String billID){

        ArrayList<BillDetail> list = new ArrayList<BillDetail>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from billdetail where billid = "+ billID, null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            BillDetail detail  = new BillDetail();
            detail.setProductName(res.getString(res.getColumnIndex(BILLDETAIL_PRODUCTNAME)));
            detail.setPrice(res.getFloat(res.getColumnIndex(BILLDETAIL_PRICE)));
            detail.setNumber(res.getInt(res.getColumnIndex(BILLDETAIL_NUMBER)));
            list.add(detail);
            res.moveToNext();
        }
        db.close();
        return list;
    }


    public int numberOfRowsBillDetailTable(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, BILLDETAIL_TABLE);
        return numRows;
    }

    public boolean insertBillDetail(BillDetail billDetail){

        int rowNumber= numberOfRowsBillTable();
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("productname",billDetail.getProductName());
        contentValues.put("billid",billDetail.getBillID());
        contentValues.put("number",billDetail.getNumber());
        contentValues.put("price",billDetail.getPrice());


        db.insert("billdetail", null, contentValues);
        if(rowNumber == numberOfRowsTableAccount()+1)
            return true;
        else
            return false;
    }

    public Integer deleteBillDetail (Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("billdetail",
                "id = ? ",
                new String[] { Integer.toString(id) });
    }

    public Cursor getBillDetailByID(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from billdetail where id="+id+"", null );
        return res;
    }


    public boolean updateBillDetail (BillDetail billdetail) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("billId",billdetail.getBillID());
        contentValues.put("number",billdetail.getNumber());
        contentValues.put("price",billdetail.getPrice());

        db.update("billdetail", contentValues, "id = ? ", new String[] { Integer.toString(billdetail.getId()) } );
        return true;
    }




/////////////////////

    public int numberOfRowsBillTable(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, BILL_TABLE);
        return numRows;
    }


    public boolean insertBill(Bill bill){

        int rowNumber= numberOfRowsBillTable();
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id",bill.getId());
        contentValues.put("date",bill.getDate());
        contentValues.put("employee",bill.getEmployee());
        contentValues.put("totalprice",bill.getTotalPrice());
        db.insert("bill", null, contentValues);
        if(rowNumber == numberOfRowsTableAccount()+1)
            return true;
        else
            return false;
    }

    public Integer deleteBill (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("bill",
                "id = ? ",
                new String[] { id});
    }

    public Cursor getBillByID(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from bill where id="+id+"", null );
        return res;
    }


    public boolean updateBill (Bill bill) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("employee", bill.getEmployee());
        contentValues.put("totalprice", bill.getTotalPrice());
        contentValues.put("date", bill.getDate());

        db.update("bill", contentValues, "id = ? ", new String[] { bill.getId() } );
        return true;
    }
/////////////
    public boolean insertAccount(Account acc){

        int rowNumber= numberOfRowsTableAccount();
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",acc.getName());
        contentValues.put("pass",acc.getPass());
        db.insert("account", null, contentValues);
        if(rowNumber == numberOfRowsTableAccount()+1)
            return true;
        else
            return false;
    }


    public boolean updateAccount (Integer id, String name, String pass) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("pass", pass);
        db.update("account", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

    public Integer deleteAccount (Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("account",
                "id = ? ",
                new String[] { Integer.toString(id) });
    }

    public Cursor getAccountByID(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from account where id="+id+"", null );
        return res;
    }

    public int numberOfRowsTableAccount(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, ACCOUNT_TABLE_ACCOUNT);
        return numRows;
    }

    public boolean checkUser(Account acc) {
        String email = acc.getName();
        String password = acc.getPass();

        // array of columns to fetch
        String[] columns = {
                ACCOUNT_COLUMN_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = ACCOUNT_COLUMN_NAME + " = ?" + " AND " + ACCOUNT_COLUMN_PASSWORDS + " = ?";

        // selection arguments
        String[] selectionArgs = {email, password};

        // query user table with conditions
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
         */
        Cursor cursor = db.query(ACCOUNT_TABLE_ACCOUNT, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        int cursorCount = cursor.getCount();

        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }

        return false;
    }

    public ArrayList<Account> getAllAccount() {
        ArrayList<Account> array_list = new ArrayList<Account>();


        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from account", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            Account acc = new Account();
            acc.setName(res.getString(res.getColumnIndex(ACCOUNT_COLUMN_NAME)));
            acc.setPass(res.getString(res.getColumnIndex(ACCOUNT_COLUMN_PASSWORDS)));
            array_list.add(acc);
            res.moveToNext();
        }
        db.close();
        return array_list;
    }

    public boolean updateProduct (Product product) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("price", product.getPrice());
        contentValues.put("image",product.getImage());
        db.update("product", contentValues, "name = ? ", new String[] { product.getName()} );
        return true;
    }

    public Integer deleteProduct (String productName) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("product",
                "name = ? ",
                new String[] { productName });
    }

    public Cursor getProductByID(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from product where id="+id+"", null );
        return res;
    }

    public int numberOfRowsTableProduct(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, PRODUCT_TABLE);
        return numRows;
    }

    ////////////////////////////
    public boolean insertProduct(Product product){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",product.getName());
        contentValues.put("price",product.getPrice());
        contentValues.put("image",product.getImage());
        db.insert("product", null, contentValues);

        return true;
    }

    public ArrayList<Product> getALlProduct(){

        ArrayList<Product> listProduct = new ArrayList<Product>();


        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from product", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            Product acc = new Product();
            acc.setId(res.getInt(res.getColumnIndex(PRODUCT_COLUMN_ID)));
            acc.setName(res.getString(res.getColumnIndex(PRODUCT_COLUMN_PRODUCTNAME)));
            acc.setPrice(res.getFloat(res.getColumnIndex(PRODUCT_COLUMN_PRODUCTPRICE)));
            acc.setImage(res.getBlob(res.getColumnIndex(PRODUCT_COLUMN_PRODUCTIMAGE)));
            listProduct.add(acc);
            res.moveToNext();
        }

        return listProduct;
    }

    /////////////////////
    public boolean inserEmployee(Employee employee){



        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",employee.getName());
        contentValues.put("address", employee.getAddress());
        contentValues.put("phonenumber", employee.getPhoneNumber());
        contentValues.put("position", employee.getPosition());
        db.insert("employee", null, contentValues);

            return true;

    }

    public Employee getEmployeeByPhoneNumber(String sdt) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from employee where phonenumber="+sdt, null );
        Employee employee = new Employee();
        res.moveToFirst();
        employee.setName(res.getString(res.getColumnIndex(EMPLOYEE_COLUMN_NAME)));
        employee.setAddress(res.getString(res.getColumnIndex(EMPLOYEE_COLUMN_ADRESS)));
        employee.setPhoneNumber(res.getString(res.getColumnIndex(EMPLOYEE_COLUMN_PHONENUMBER)));
        employee.setPosition(res.getString(res.getColumnIndex(EMPLOYEE_COLUMN_POSITION)));
        return employee;
    }

    public int numberOfRowsEmployeeTable(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, EMPLOYEE_TABLE);
        return numRows;
    }

    public ArrayList<Employee> getAllEmployee() {

        ArrayList<Employee> array_list = new ArrayList<Employee>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from employee", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            Employee employee = new Employee();
            employee.setName(res.getString(res.getColumnIndex(EMPLOYEE_COLUMN_NAME)));
            employee.setAddress(res.getString(res.getColumnIndex(EMPLOYEE_COLUMN_ADRESS)));
            employee.setPhoneNumber(res.getString(res.getColumnIndex(EMPLOYEE_COLUMN_PHONENUMBER)));
            employee.setPosition(res.getString(res.getColumnIndex(EMPLOYEE_COLUMN_POSITION)));
            array_list.add(employee);
            res.moveToNext();
        }
        db.close();
        return array_list;
    }

    public boolean updateEmployee(Employee employee) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", employee.getName());
        contentValues.put("address", employee.getAddress());
        contentValues.put("position", employee.getPosition());

        db.update("employee", contentValues, "phonenumber = ? ", new String[] { employee.getPhoneNumber() } );
        return true;
    }

    public Integer deleteEmployee (String sdt) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("employee",
                "phonenumber = ? ",
                new String[] { sdt });
    }


    public ArrayList<Bill> getAllBill(){

        ArrayList<Bill> list = new ArrayList<Bill>();


        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from bill", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            Bill bill = new Bill();
            bill.setId(res.getString(res.getColumnIndex(BILL_COLUMN_ID)));
            bill.setTotalPrice(res.getFloat(res.getColumnIndex(BILL_COLUMN_TOTALPRICE)));
            bill.setDate(res.getString(res.getColumnIndex(BILL_COLUMN_DATE)));
            list.add(bill);
            res.moveToNext();
        }
        db.close();



        return list;
    }

}
