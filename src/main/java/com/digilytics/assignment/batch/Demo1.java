package com.digilytics.assignment.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.digilytics.assignment.entity.User;
import com.digilytics.assignment.entity.view.UserRequest;

@Configuration
public class Demo1 {

	private JobBuilderFactory jobBuilderFactory;
	private StepBuilderFactory stepBuilderFactory;
	private UserProcessor userProcessor;
	private UserDBWriter userDBWriter;

	@Autowired
	public Demo1(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory,
			UserProcessor userProcessor, UserDBWriter userDBWriter) {
		this.jobBuilderFactory = jobBuilderFactory;
		this.stepBuilderFactory = stepBuilderFactory;
		this.userProcessor = userProcessor;
		this.userDBWriter = userDBWriter;
	}

	@Qualifier(value = "demo1")
	@Bean
	public Job demo1Job() throws Exception {
		return this.jobBuilderFactory.get("demo1").start(step1Demo1()).build();
	}

	@Bean
	public Step step1Demo1() throws Exception {
		return this.stepBuilderFactory.get("step1").<UserRequest, User>chunk(5).reader(employeeReader())
				.processor(userProcessor).writer(userDBWriter).build();
	}

	@Bean
	@StepScope
	Resource inputFileResource(@Value("#{jobParameters[fileName]}") final String fileName) throws Exception {
		return new ClassPathResource(fileName);
	}

	@Bean
	@StepScope
	public FlatFileItemReader<UserRequest> employeeReader() throws Exception {
		FlatFileItemReader<UserRequest> reader = new FlatFileItemReader<>();
		reader.setResource(inputFileResource(null));
		reader.setName("CSV Reader");
		reader.setLinesToSkip(1);
		reader.setLineMapper(lineMapper());
		return reader;
	}

	@Bean
	@StepScope
	public LineMapper<UserRequest> lineMapper() {

		DefaultLineMapper<UserRequest> defaultLineMapper = new DefaultLineMapper<UserRequest>();
		DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();

		lineTokenizer.setDelimiter(",");
		lineTokenizer.setStrict(false);
		lineTokenizer.setNames(new String[] { "email", "name", "roles" });

		UserFileRowMapper fieldSetMapper = new UserFileRowMapper();
		// fieldSetMapper.setTargetType(UserRequest.class);

		defaultLineMapper.setLineTokenizer(lineTokenizer);
		defaultLineMapper.setFieldSetMapper(fieldSetMapper);
		return defaultLineMapper;
	}

}