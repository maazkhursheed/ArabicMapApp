package com.example.arabicmapapp;

public class FavouriteModel {

	int _id;
    String _categoryFavourite;
    String _sitesFavourite;
    String _linksFavourite;
    
    public FavouriteModel(){
    	
    }
    
    public FavouriteModel(int id, String categoryFavourite, String sitesFavourite, String linksFavourite){
    	
    	this._id = id;
    	this._categoryFavourite = categoryFavourite;
    	this._sitesFavourite = sitesFavourite;
    	this._linksFavourite = linksFavourite;
      }
    
    public FavouriteModel(String categoryFavourite, String sitesFavourite, String linksFavourite){
    	
    	this._categoryFavourite = categoryFavourite;
    	this._sitesFavourite = sitesFavourite;
    	this._linksFavourite = linksFavourite;
      }
    
    public int getID(){
          return this._id;
      }
 

    public void setID(int id){
          this._id = id;
      }
    
    public String getCategoryFavourite(){
        return this._categoryFavourite;
    }
     
    
    public void setCategoryFavourite(String categoryFavourite){
        this._categoryFavourite = categoryFavourite;
    }
    
    public String getSitesFavourite(){
        return this._sitesFavourite;
    }
     
   
    public void setSitesFavourite(String sitesFavourite){
        this._sitesFavourite = sitesFavourite;
    }
    
    public String getLinksFavourite(){
        return this._linksFavourite;
    }
    
    public void setLinksFavourite(String linksFavourite){
        this._linksFavourite = linksFavourite;
    }
}
