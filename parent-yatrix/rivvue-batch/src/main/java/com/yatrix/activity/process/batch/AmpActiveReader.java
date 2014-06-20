package com.yatrix.activity.process.batch;

import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.item.database.AbstractPagingItemReader;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.yatrix.activity.store.mongo.domain.ZipCodes;
import com.yatrix.activity.store.mongo.repository.ZipCodeRepository;

@Service
public class AmpActiveReader extends AbstractPagingItemReader<ZipCodes> 
implements InitializingBean {
	
	private static final Log log = LogFactory.getLog(AmpActiveReader.class);

	@Autowired
	private ZipCodeRepository zipCodeRepository;
	
	public AmpActiveReader(){
		super();
		setPageSize(50);
	}

	@Override
	protected void doReadPage() {
		if (results == null) {
			results = new CopyOnWriteArrayList<ZipCodes>();
		}
		else {
			results.clear();
		}
		System.out.println("Records From: " + ((getPage() * getPageSize())+ 1 ));

//		log.info(" ---------------------------------------------------- Amp Active Reader --------------------------------------------" );
//		System.out.println(" ---------------------------------------------------- Amp Active Reader --------------------------------------------" );
		
		Page<ZipCodes> list = zipCodeRepository.findAll(new PageRequest(getPage() , getPageSize()));
		
		for(ZipCodes zipCodes : list){
			results.add(zipCodes);	
		}
	}

	@Override
	protected void doJumpToPage(int itemIndex) {
		// TODO Auto-generated method stub
		
	}
	
}
