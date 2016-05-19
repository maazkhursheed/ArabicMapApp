package com.example.arabicmapapp;

public class ListModel {
	
//	 private  String CompanyName="";
//     private  String Image="";
//     private  String Url="";
     
     int _id;
     String _category;
     String _sites;
     String _links;
      
    
      
     public ListModel(){
         
     }
     
     public ListModel(int _id, String category, String sites , String links){
         this._id = _id;
         this._category = category;
         this._sites = sites;
         this._links = links;
     }
      
     // constructor
     public ListModel(String category, String sites , String links){
         this._category = category;
         this._sites = sites;
         this._links = links;
     }
     
     public int getID(){
         return this._id;
     }
      
     public void setID(int _id){
         this._id = _id;
     }
     
     public String getCategory(){
         return this._category;
     }
      
     public void setCategory(String category){
         this._category = category;
     }
     
     public String getSites(){
         return this._sites;
     }
      
    
     public void setSites(String sites){
         this._sites = sites;
     }
     
     public String getLinks(){
         return this._links;
     }
      
     // setting name
     public void setLinks(String links){
         this._links = links;
     }

}
