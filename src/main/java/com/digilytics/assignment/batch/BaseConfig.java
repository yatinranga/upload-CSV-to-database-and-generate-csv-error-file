package com.digilytics.assignment.batch;

import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;

@Configuration
@Async
public class BaseConfig {

	public JobRepository jobRepository;

	@Autowired
	public BaseConfig(JobRepository jobRepository) {
		this.jobRepository = jobRepository;
	}

	@Bean
	public JobLauncher simpleJobLauncher() {
		SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
		jobLauncher.setJobRepository(jobRepository);
		return jobLauncher;
	}

	/*
	 * @Bean public Job job(JobBuilderFactory jobBuilderFactory, StepBuilderFactory
	 * stepBuilderFactory, ItemReader<UserRequest> itemReader,
	 * ItemProcessor<UserRequest, User> itemProcessor, UserDBWriter userDBWriter) {
	 * 
	 * Step step = stepBuilderFactory.get("ETL-file-load").<UserRequest,
	 * User>chunk(5).reader(itemReader)
	 * .processor(itemProcessor).writer(userDBWriter).build();
	 * 
	 * Job job = jobBuilderFactory.get("ETL-Load").incrementer(new
	 * RunIdIncrementer()).start(step).build();
	 * 
	 * return job; }
	 * 
	 * @Bean public FlatFileItemReader<UserRequest> itemReader(@Value("${input}")
	 * Resource resource) {
	 * 
	 * FlatFileItemReader<UserRequest> flatFileItemReader = new
	 * FlatFileItemReader<UserRequest>(); flatFileItemReader.setResource(resource);
	 * flatFileItemReader.setName("CSV Reader");
	 * flatFileItemReader.setLinesToSkip(1);
	 * flatFileItemReader.setLineMapper(lineMapper());
	 * 
	 * return flatFileItemReader;
	 * 
	 * }
	 * 
	 * @Bean public LineMapper<UserRequest> lineMapper() {
	 * 
	 * DefaultLineMapper<UserRequest> defaultLineMapper = new
	 * DefaultLineMapper<UserRequest>(); DelimitedLineTokenizer lineTokenizer = new
	 * DelimitedLineTokenizer();
	 * 
	 * lineTokenizer.setDelimiter(","); lineTokenizer.setStrict(false);
	 * lineTokenizer.setNames(new String[] { "email", "name", "roles" });
	 * 
	 * UserFileRowMapper fieldSetMapper = new UserFileRowMapper(); //
	 * fieldSetMapper.setTargetType(UserRequest.class);
	 * 
	 * defaultLineMapper.setLineTokenizer(lineTokenizer);
	 * defaultLineMapper.setFieldSetMapper(fieldSetMapper); return
	 * defaultLineMapper; }
	 */
}
