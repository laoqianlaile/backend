package com.elex.oa;

import com.elex.oa.entity.project.ProjectCode;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Calendar;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OaApplicationTests {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Test
	public void contextLoads() {

	}

	@Test
	public void weekValidate() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_WEEK,Calendar.MONDAY);
		String start = simpleDateFormat.format(calendar.getTime());
		Calendar calendar1 = Calendar.getInstance();
		calendar1.set(Calendar.DAY_OF_WEEK,Calendar.SATURDAY);
		String end = simpleDateFormat.format(calendar1.getTime());
		System.out.println(start);
		System.out.println(end);
	}

	@Test
	public void simplateValidate() {
		mongoTemplate.dropCollection(ProjectCode.class);
	}

}
