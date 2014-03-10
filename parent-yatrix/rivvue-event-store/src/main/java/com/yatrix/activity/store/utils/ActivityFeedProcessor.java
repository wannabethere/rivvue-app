package com.yatrix.activity.store.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import javax.inject.Inject;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.yatrix.activity.store.mongo.domain.Activity;
import com.yatrix.activity.store.mongo.domain.Category;


public class ActivityFeedProcessor {

	private final static String FEEDFILENAME="Activities.txt";
	private final static String LINEINDENT="\t";
	private Map<String,List<String>> categoryToActivityMap=new HashMap<String,List<String>>();
	private final static String CATEGORY_PREFIX="C";
	private final static String SUB_CATEGORY_PREFIX="CS";

	private final MongoTemplate mongoTemplate;
	private final CounterService counterService;



	@Inject
	public ActivityFeedProcessor(MongoTemplate mongoTemplate,CounterService counterservice){
		this.counterService=counterservice;
		this.mongoTemplate=mongoTemplate;
	}

	public void init(){
		List<Category> dbCategories = mongoTemplate.findAll(Category.class);
		if(dbCategories!=null && dbCategories.size()>0){
			return;
		}
		try {
			this.readFile();
			if(this.categoryToActivityMap.size()>0){
				
				mongoTemplate.dropCollection("ActivityCategory");
				mongoTemplate.dropCollection("Activities");
				for(String key:this.categoryToActivityMap.keySet()){
					this.processCategory(key, this.categoryToActivityMap.get(key));
				}

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block Create Exception
			e.printStackTrace();
		}


	}

	private void processCategory(String category, List<String> subCategoryList){
		long sequenceId=this.counterService.getNextUserIdSequence();
		Category mcategory=new Category();
		mcategory.setId(CATEGORY_PREFIX+sequenceId+"");
		//mcategory.setUuid(mcategory.getId());
		//BADLY Coded.Need to use REGEX split.
		mcategory.setDisplayName(category.substring(0,category.indexOf("(")).trim());
		mcategory.setCategoryName(category.substring(category.indexOf("(")+1, category.indexOf(")")).trim());
		mongoTemplate.insert(mcategory, "ActivityCategory");
		if(subCategoryList.size()>0){
			for(String subcategory: subCategoryList){
				Activity activity=new Activity();
				sequenceId=this.counterService.getNextUserIdSequence();
				activity.setId(SUB_CATEGORY_PREFIX+sequenceId);
				activity.setCategoryId(mcategory.getId());
				activity.setDisplayName(subcategory.substring(0,subcategory.indexOf("(")).trim());
				activity.setSubCategory(subcategory.substring(subcategory.indexOf("(")+1, subcategory.indexOf(")")).trim());
				mongoTemplate.insert(activity, "Activities");
			}
		}

	}

	



	public  void readFile() throws IOException{

		BufferedReader input = new BufferedReader(
				new FileReader(FEEDFILENAME));
		String content;
		String currentCategory="NOCATEGORY";
		List<String> subCategory=new ArrayList<String>();
		while((content=input.readLine())!=null){
			if(content.startsWith(LINEINDENT)){
				subCategory=categoryToActivityMap.get(currentCategory);
				subCategory.add(content.trim().toLowerCase());
			}
			else{
				currentCategory=content.trim().toLowerCase();
				subCategory=new ArrayList<String>();
			}
			categoryToActivityMap.put(currentCategory, subCategory);

		}

		input.close();


	}

	public Map<String,List<String>> getCategoryToActivityMap(){
		return this.categoryToActivityMap;
	}
	
	
	
	


	public static void main(String[] args){

		String subcategory="Amateur Sports Teams (amateursportsteams)";
		Activity activity=new Activity();
		long sequenceId=121212121L;
		activity.setId(SUB_CATEGORY_PREFIX+sequenceId);
		activity.setDisplayName(subcategory.substring(0,subcategory.indexOf("(")));
		activity.setSubCategory(subcategory.substring(subcategory.indexOf("(")+1, subcategory.indexOf(")")-1));
		System.out.println(activity.getDisplayName()+"   "+ activity.getSubCategory());

		Map<String,List<String>> dummyTest=new HashMap<String,List<String>>();
		List<String> categories=new ArrayList<String>();
		BufferedReader input;
		try {
			input = new BufferedReader(
					new FileReader(FEEDFILENAME));

			String content;
			String currentCategory="NOCATEGORY";
			List<String> subCategory=new ArrayList<String>();
			while((content=input.readLine())!=null){
				if(content.startsWith(LINEINDENT)){
					subCategory=dummyTest.get(currentCategory);
					subCategory.add(content.trim());
				}
				else{

					currentCategory=content;
					System.out.println(currentCategory);
					categories.add(currentCategory);
					subCategory=new ArrayList<String>();
				}
				dummyTest.put(currentCategory, subCategory);

			}

			input.close();

			System.out.println(categories.size());
			System.out.println(dummyTest.size());
			for(String key: dummyTest.keySet() ){
				System.out.println(key+ ": " + key.substring(0,key.indexOf("(")).trim() + "= "+ key.substring(key.indexOf("(")+1, key.indexOf(")")).trim());


			}



		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		/*//ActivityFeedProcessor afp = new ActivityFeedProcessor();
		try {
			afp.readFile();
			System.out.println(afp.getCategoryToActivityMap());
			for(String categories: afp.getCategoryToActivityMap().keySet()){
				System.out.println("Category: "+ categories + "=");
				for(String subCategory: afp.getCategoryToActivityMap().get(categories)){
					System.out.print(subCategory+",");
				}
				System.out.println();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
}
