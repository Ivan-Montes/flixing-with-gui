package ime.flixing.tool;

import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DocumentFilterFactoryTest {

	@Test
	void documentFilterFactory_createDocumentFilter_ReturnObject() {
		
		assertAll(
				()-> Assertions.assertThat(DocumentFilterFactory.createDocumentFilter("")).isNotNull(),
				()-> Assertions.assertThat(DocumentFilterFactory.createDocumentFilter(CheckerPattern.DIGITS_BASIC)).isNotNull()
				);
		
	}

}
