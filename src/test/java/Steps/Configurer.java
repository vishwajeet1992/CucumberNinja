package Steps;

/*import com.fasterxml.jackson.databind.ObjectMapper;
import dto.ContactDetailsDTO;
import io.cucumber.core.api.TypeRegistry;
import io.cucumber.core.api.TypeRegistryConfigurer;
import io.cucumber.cucumberexpressions.ParameterByTypeTransformer;
import io.cucumber.cucumberexpressions.ParameterType;
import io.cucumber.datatable.DataTableType;
import io.cucumber.datatable.TableCellByTypeTransformer;
import io.cucumber.datatable.TableEntryByTypeTransformer;
import io.cucumber.docstring.DocStringType;

import java.util.Locale;
import java.util.Map;

import static java.util.Locale.ENGLISH;

public class Configurer implements TypeRegistryConfigurer {

    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Locale locale() {
        return ENGLISH;
    }

    @Override
    public void configureTypeRegistry(TypeRegistry typeRegistry) {
        typeRegistry.defineDataTableType(new DataTableType(
                ContactDetailsDTO.class,
                (Map<String, String> row) -> objectMapper.convertValue(row, ContactDetailsDTO.class))
        );
    }*/

