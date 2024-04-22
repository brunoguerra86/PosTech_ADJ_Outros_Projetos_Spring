package br.com.fiap.handsonspringbatch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.sql.DataSource;

@Configuration
public class BatchConfiguration {

    @Bean
    public Job processarPerson(JobRepository jobRepository, Step step){
        return new JobBuilder("processarPerson", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(step)
                .build();
    }

    @Bean
    public Step step(JobRepository jobRepository,
                     PlataformTransactionManager plataformTransactionManager,
                     ItemReader<Person> itemReader,
                     ItemWriter<Person> itemWriter,
                     ItemProcessor<Person, Person> itemProcessor){
        return new StepBuilder("step", jobRepository )
                .<Person, Person>chunk
                        (20, plataformTransactionManager)
                .reader(itemReader)
                .processor(itemProcessor)
                .writer(itemWriter)
                .build();
    }

    @Bean
    public ItemReader<Person> itemReader(){
        BeanWrapperFieldSetMapper<Person> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(Person.class);

        return new FlatFileItemReaderBuilder<Person>()
                .name("personItemReader")
                .resource(new ClassPathResource("people.csv"))
                .delimited()
                .names("namePerson","streetName","streetNumber", "city", "country", "email", "phoneNumber")
                .fieldSetMapper(fieldSetMapper)
                .build();
    }

    @Bean
    public ItemWriter<Person> itemWriter(DataSource dataSource){
        return new JdbcBatchItemWriterBuilder<Person>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .dataSource(dataSource)
                .sql("INSERT INTO person " +
                        "(name_person, street_name, street_number, city, country, email, phone_number, create_date_time) " +
                        "VALUES (:namePerson, :streetName, :streetNumber, :city, :country, :email, :phoneNumber, :createDateTime)")
                .build();
    }

    @Bean
    public ItemProcessor<Person, Person> itemProcessor(){
        return new PersonProcessor();
    }
}
