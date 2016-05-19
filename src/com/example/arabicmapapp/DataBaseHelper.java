package com.example.arabicmapapp;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.R.bool;
import android.content.ContentValues;
import android.content.Context;
import android.content.ContextWrapper;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.util.Log;

import com.example.arabicmapapp.ListModel;

public class DataBaseHelper extends SQLiteOpenHelper {
	
	  //The Android's default system path of your application database.
	
    private static String DB_PATH = "/data/data/com.example.arabicmapapp/databases/";
 
    private static String DB_NAME = "ArabicDB.sqlite";
    
    private static final String TABLE_NAME = "ArabicTable";

    private static final String  COLUMN_ID = "_id";
    private static final String  COLUMN_NAME1= "category";
    private static final String  COLUMN_NAME2= "sites";
    private static final String  COLUMN_NAME3= "links";
    
    public static final String KEY_Category ="حسابات";
    
    private static final String FAV_TABLE_NAME = "FavouriteTable";
    
    private static final String  FAV_ID = "id";
    private static final String  CAT_FAV= "categoryFavourite";
    private static final String  SITE_FAV= "sitesFavourite";
    private static final String  LINK_FAV= "linksFavourite";
    
    private SQLiteDatabase myDataBase; 
 
    private final Context myContext;

	public DataBaseHelper(Context context) {
		super(context, DB_NAME, null, 2);
        this.myContext = context;
	}

	@Override
	public void onCreate(SQLiteDatabase myDataBase) {
		
		 String CREATE_FAVS_TABLE = "CREATE TABLE " + FAV_TABLE_NAME + "("
	                + FAV_ID + " INTEGER PRIMARY KEY,"
				    + CAT_FAV + " TEXT,"
	                + SITE_FAV + " TEXT,"
				    + LINK_FAV + " TEXT"+ ")";
		 myDataBase.execSQL(CREATE_FAVS_TABLE);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase myDataBase, int oldVersion, int newVersion) {
		 // Drop older table if existed
		myDataBase.execSQL("DROP TABLE IF EXISTS " + FAV_TABLE_NAME);
 
        // Create tables again
        onCreate( myDataBase);
		
	}
	
	 // Adding new favourite
	public void addFavourite(FavouriteModel favModel) {
	    SQLiteDatabase db = this.getWritableDatabase();
	 
	    ContentValues values = new ContentValues();
	    values.put(CAT_FAV, favModel.getCategoryFavourite()); // category favourite
	    values.put(SITE_FAV, favModel.getSitesFavourite()); // site favourite
	    values.put(LINK_FAV, favModel.getLinksFavourite()); // link favourite
	    // Inserting Row
	    db.insert(FAV_TABLE_NAME, null, values);
	    db.close(); // Closing database connection
	}
	
	// Getting single favourite
	public FavouriteModel getFavourite(int id) {
	    SQLiteDatabase db = this.getReadableDatabase();
	 
	    Cursor cursor = db.query(FAV_TABLE_NAME, new String[] { FAV_ID,
	    		CAT_FAV, SITE_FAV, LINK_FAV }, FAV_ID + "=?",
	            new String[] { String.valueOf(id) }, null, null, null, null);
	    if (cursor != null)
	        cursor.moveToFirst();
	 
	    FavouriteModel favourite = new FavouriteModel(Integer.parseInt(cursor.getString(0)),
	                                   cursor.getString(1), cursor.getString(2), cursor.getString(3));
	    
	    // return favourite
	    return favourite;
	}
	
	// Getting All favourites
	 public List<FavouriteModel> getAllFavourites() {
	    List<FavouriteModel> favList = new ArrayList<FavouriteModel>();
	    
	    // Select All Query
	    String selectQuery = "SELECT  * FROM " + FAV_TABLE_NAME;
	 
	    SQLiteDatabase db = this.getWritableDatabase();
	    Cursor cursor = db.rawQuery(selectQuery, null);
	 
	    // looping through all rows and adding to list
	    if (cursor.moveToFirst()) {
	        do {
	        	FavouriteModel favModel = new FavouriteModel();
	        	favModel.setID(Integer.parseInt(cursor.getString(0)));
	        	favModel.setCategoryFavourite(cursor.getString(1));
	        	favModel.setSitesFavourite(cursor.getString(2));
	            favModel.setLinksFavourite(cursor.getString(3));
	        	
	        	// Adding contact to list
	        	favList.add( favModel);
	        } while (cursor.moveToNext());
	    }
	 
	    // return contact list
	    return favList;
	}
	 
	// Getting favourites Count
	    public int getFavouritesCount(String categoryName) {
	    	
	    	 int count = 0;
	    	
	        String countQuery = "SELECT  * FROM " + FAV_TABLE_NAME + " WHERE categoryFavourite = '" + categoryName +"'";
	        SQLiteDatabase db = this.getReadableDatabase();
	        Cursor cursor = db.rawQuery(countQuery, null);
	        
	        if(cursor != null && !cursor.isClosed()){
	            count = cursor.getCount();
	            cursor.close();
	        }   
	        return count;
//	        cursor.close();
//	 
//	        // return count
//	        return cursor.getCount();
	    }
	    
	 // Updating single favourite
	    public int updateFavourite(FavouriteModel favModel) {
	        SQLiteDatabase db = this.getWritableDatabase();
	     
	        ContentValues values = new ContentValues();
	        values.put(CAT_FAV, favModel.getCategoryFavourite()); // category favourite
		    values.put(SITE_FAV, favModel.getSitesFavourite()); // site favourite
	        values.put(LINK_FAV, favModel.getLinksFavourite()); // link favourite
		    
	        // updating row
	        return db.update(FAV_TABLE_NAME, values, FAV_ID + " = ?",
	                new String[] { String.valueOf(favModel.getID()) });
	    }
	    
	 // Deleting single favourite
	    public void deleteFavourite(String categoryName, String siteName) {
	        SQLiteDatabase db = this.getWritableDatabase();
	        String countQuery = "DELETE FROM " + FAV_TABLE_NAME + " WHERE categoryFavourite = '" + categoryName +"' and sitesFavourite = '" + siteName +"'";
	        db.execSQL(countQuery);
	        
//	       int check =  db.delete(FAV_TABLE_NAME,"WHERE categoryFavourite =?  and sitesFavourite =? " , new String[] { categoryName,siteName});
//	       Log.i("number of rows deleted ",""+ check);
	       //	        String countQuery = "DELETE FROM " + FAV_TABLE_NAME + " WHERE categoryFavourite = '" + categoryName +"' and sitesFavourite = '" + siteName +"'";
//	        Cursor mCursor = db.rawQuery(countQuery, null);
//	        
	        
//	        db.delete(FAV_TABLE_NAME,  CAT_FAV + " = ?"+ SITE_FAV + " = ?",
//	                new String[] { favModel.getCategoryFavourite(),favModel.getSitesFavourite() });
	        db.close();
	    }
	    
	    
	    public boolean checkFavourite(String categoryName, String siteName){
	    
	    	String countQuery = "SELECT  * FROM " + FAV_TABLE_NAME + " WHERE categoryFavourite = '" + categoryName +"' and sitesFavourite = '" + siteName +"'";
	    	SQLiteDatabase db = this.getReadableDatabase();
	    	Cursor mCursor = db.rawQuery(countQuery, null);
	    	
	    	if(mCursor.getCount() != 0){
	    		return true;
	    	}
	    	else{
	    		return false;
	    	}
	    }
	   
	
	
	
	public void createDataBase() throws IOException{
		 
    	boolean dbExist = checkDataBase();
 
    	if(dbExist){
    		//do nothing - database already exist
    	}else{
 
    		//By calling this method and empty database will be created into the default system path
               //of your application so we are gonna be able to overwrite that database with our database.
        
    	//	this.getReadableDatabase();
 
        	this.getWritableDatabase();
        	try { 
 
    			copyDataBase();
 
    		} catch (IOException e) {
 
        		throw new Error("Error copying database");
 
        	}
    	}
 
    }
	
	
	private boolean checkDataBase(){
		 
    	SQLiteDatabase checkDB = null;
 
    	try{
    		String myPath = DB_PATH + DB_NAME;
    		checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
    		checkDB.setLocale(Locale.getDefault());
    		checkDB.setLockingEnabled(true);
    		checkDB.setVersion(1);
    	}catch(SQLiteException e){
 
    		//database does't exist yet.
 
    	}
 
    	if(checkDB != null){
 
    		checkDB.close();
 
    	}
 
    	return checkDB != null ? true : false;
    }
	
	
private void copyDataBase() throws IOException{
		
		InputStream myInput = null;
        OutputStream myOutput = null;
		String dbFilePath = DB_PATH + DB_NAME;	// Path to the just created empty db
    
		try{ 
			
	    myInput = myContext.getAssets().open(DB_NAME); //Open your local db as the input stream
        myOutput = new FileOutputStream(dbFilePath);  //Open the empty db as the output stream
 
       	byte[] buffer = new byte[1024];  //transfer bytes from the inputfile to the outputfile
    	int length;
    	while ((length = myInput.read(buffer))>0){
    		
    		myOutput.write(buffer, 0, length);
    	}
 
    	//Close the streams
    	myOutput.flush();
    	myOutput.close();
    	myInput.close();
    	
		}
		
		catch(IOException e){
			
			throw new Error("problem copyingg database from resource file");
		}
   	   
 
    }



public void openDataBase() throws SQLException{
		 
	    	//Open the database
	        String myPath = DB_PATH + DB_NAME;
	    	myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
	 
	    }

//Getting single category

	 @Override
		public synchronized void close() {
	 
	    	    if(myDataBase != null){
	    	    	
	    	    	myDataBase.close();
	    	    }
	    		    
	 
	    	    super.close();
	 
		}
	 


      public ListModel getCategory(int id) {
             SQLiteDatabase db = this.getReadableDatabase();
 
             Cursor cursor = db.query(TABLE_NAME, new String[] { COLUMN_ID, COLUMN_NAME1, COLUMN_NAME2, COLUMN_NAME3}, COLUMN_ID + "=?",
             new String[] { String.valueOf(id) }, null, null, null, null);
          if (cursor != null)
              cursor.moveToFirst();
 
          ListModel model = new ListModel(Integer.parseInt(cursor.getString(0)),cursor.getString(1), cursor.getString(2), cursor.getString(3));
    
          return model;
        }
      
      public List<ListModel> getAllCategories() {
    	    List<ListModel> categoryList = new ArrayList<ListModel>();
    	    // Select All Query
    	    String selectQuery = "SELECT category,sites FROM ArabicTable group by category";
    	    
    	    
    	    SQLiteDatabase db = this.getWritableDatabase();
    	    Cursor cursor = db.rawQuery(selectQuery, null);
    	 
    	    // looping through all rows and adding to list
    	    if (cursor.moveToFirst()) {
    	        do {
    	            ListModel model = new ListModel();
    	           // model.setID(Integer.parseInt(cursor.getString(0)));
    	            model.setCategory(cursor.getString(0));
    	            model.setSites(cursor.getString(1));
    	           // model.setLinks(cursor.getString(3));
    	            // Adding contact to list
    	            categoryList.add(model);
    	        } while (cursor.moveToNext());
    	    }
    	   
    	    
    	    // return category list
    	    return categoryList;
    	}
      
      public List<ListModel> getAllSites(String categoryName){
    	  List<ListModel> siteList = new ArrayList<ListModel>();
    	  String selectQuery = "SELECT distinct(category),sites,links,_id FROM ArabicTable WHERE category = '" + categoryName +"'";
    	  
    	SQLiteDatabase db = this.getWritableDatabase();
  	    Cursor cursor = db.rawQuery(selectQuery, null);
  	 
  	    // looping through all rows and adding to list
  	    if (cursor.moveToFirst()) {
  	 
  	    	 do {
 	            ListModel model = new ListModel();
 	           // model.setID(Integer.parseInt(cursor.getString(0)));
 	            model.setCategory(cursor.getString(0));
 	            model.setSites(cursor.getString(1));
  	            model.setLinks(cursor.getString(2));
 	            model.setID(Integer.parseInt(cursor.getString(3)));
 	            // Adding contact to list
 	            siteList.add(model);
 	        } while (cursor.moveToNext());
  	    }

  	  return siteList;
  }
      public int getCategories_SitesCount(String categoryName) {
    	  
    	  int count = 0;
    	  
          String countQuery = "SELECT* FROM ArabicTable WHERE category = '" + categoryName +"'";
          SQLiteDatabase db = this.getReadableDatabase();
          Cursor cursor = db.rawQuery(countQuery, null);
             
          if(cursor != null && !cursor.isClosed()){
	            count = cursor.getCount();
	            cursor.close();
	        }   
	        return count;
          
//          cursor.close();
//   
//          // return count
//          return cursor.getCount();
      }
      
      public List<ListModel> getAllSiteItems(){
    	  List<ListModel> siteList = new ArrayList<ListModel>();
    	  String selectQuery = "SELECT * FROM ArabicTable";
    	  
    	SQLiteDatabase db = this.getWritableDatabase();
  	    Cursor cursor = db.rawQuery(selectQuery, null);
  	 
  	    // looping through all rows and adding to list
  	    if (cursor.moveToFirst()) {
  	 
  	    	 do {
 	            ListModel model = new ListModel();
 	            model.setID(Integer.parseInt(cursor.getString(0)));
 	            model.setCategory(cursor.getString(1));
 	            model.setSites(cursor.getString(2));
  	            model.setLinks(cursor.getString(3));
 	           // model.setID(Integer.parseInt(cursor.getString(3)));
 	            // Adding contact to list
 	            siteList.add(model);
 	        } while (cursor.moveToNext());
  	    }

  	  return siteList;
  }
	
	
	 public Cursor getCursor(){

	        SQLiteQueryBuilder queryBulider = new SQLiteQueryBuilder();
	        queryBulider .setTables(TABLE_NAME);
	        String[] asColumnsToReturn = new String[] {COLUMN_ID,COLUMN_NAME1,COLUMN_NAME2,COLUMN_NAME3};
	        Cursor mCursor =queryBulider.query(myDataBase, asColumnsToReturn, null, null,         null,null,"cat_name ASC" );
	        return mCursor;
	    }
	 
	 public String getName(Cursor c) {

	        return(c.getString(1));
	    }
	 
	

}
