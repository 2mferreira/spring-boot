/*
 * Copyright 2012-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.boot.test.autoconfigure.orm.jpa;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.properties.PropertyMapping;

/**
 * Annotation that can be applied to a test class to configure a test database to use
 * instead of any application defined or auto-configured {@link DataSource}.
 *
 * @author Phillip Webb
 * @see TestDatabaseAutoConfiguration
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.METHOD })
@ImportAutoConfiguration(TestDatabaseAutoConfiguration.class)
@PropertyMapping("spring.test.database")
public @interface AutoConfigureTestDatabase {

	/**
	 * If the test database should replace any existing.
	 */
	Replace replace() default Replace.ANY;

	/**
	 * The type of connection to be establish when {@link #replace() replacing} the data
	 * source. By default will attempt to detect the connection based on the classpath.
	 */
	EmbeddedDatabaseConnection connection() default EmbeddedDatabaseConnection.NONE;

	/**
	 * What the test database can replace.
	 */
	enum Replace {

		/**
		 * Replace any DataSource bean (auto-configured or manually defined).
		 */
		ANY,

		/**
		 * Only replace auto-configured DataSource.
		 */
		AUTO_CONFIGURED,

		/**
		 * Don't replace the application default DataSource.
		 */
		NONE

	}

}
