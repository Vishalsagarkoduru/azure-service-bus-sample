package com.coderjeet.microservicerabbitMQ.configuration;

import com.azure.identity.DefaultAzureCredential;
import com.azure.identity.DefaultAzureCredentialBuilder;
import com.azure.security.keyvault.secrets.SecretClient;
import com.azure.security.keyvault.secrets.SecretClientBuilder;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfigurationForKeyVault {

	@Value("${spring.cloud.azure.keyvault.secret.property-sources[0].endpoint}")
	private String vaultUri;
	/**
	 * The default credential first checks environment variables for configuration.
	 * If environment configuration is incomplete, it will try managed identity.
	 */
		@Bean
		public SecretClient createSecretClient() {
			return new SecretClientBuilder()
					.vaultUrl(vaultUri)
					.credential(new DefaultAzureCredentialBuilder().build())
					.buildClient();
		}
}
