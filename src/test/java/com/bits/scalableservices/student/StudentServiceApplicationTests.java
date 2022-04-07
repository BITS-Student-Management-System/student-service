package com.bits.scalableservices.student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.net.URI;
import java.net.URISyntaxException;

import javax.jms.JMSException;
import javax.jms.Message;

import org.apache.activemq.broker.BrokerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;

import com.bits.scalableservices.student.VO.StudentRequest;
import com.bits.scalableservices.student.config.JMSConfig;
import com.bits.scalableservices.student.entity.Student;
import com.bits.scalableservices.student.repository.StudentRepository;

@EnableAutoConfiguration
@Import({ JMSConfig.class, TestConfig.class })
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@EnableJms
class StudentServiceApplicationTests {

	@LocalServerPort
	int randomServerPort;

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private JmsTemplate jmsTemplate;

	@Test
	public void saveStudent() throws URISyntaxException, JMSException, InterruptedException {
		final String baseUrl = "http://localhost:" + randomServerPort + "/students/create";
		URI uri = new URI(baseUrl);

		StudentRequest studentRequest = new StudentRequest();
		studentRequest.setFirstName("Eisha");
		studentRequest.setLastName("Shetty");
		studentRequest.setEmailAddress("abc@gmail.com");
		studentRequest.setDepartmentId(1l);
		restTemplate.postForLocation(uri, studentRequest);

		Thread.sleep(2000);

		Student student = studentRepository.findByDepartmentId(1l);
		assertEquals("Eisha", student.getFirstName());

		Message message = jmsTemplate.receive("department-queue");
		assertNotNull(message);
	}
}

@Configuration
class TestConfig {

	@Bean
	public BrokerService broker() throws Exception {
		BrokerService broker = new BrokerService();
		broker.addConnector("tcp://localhost:61616");
		broker.setPersistent(false);
		return broker;
	}
}
