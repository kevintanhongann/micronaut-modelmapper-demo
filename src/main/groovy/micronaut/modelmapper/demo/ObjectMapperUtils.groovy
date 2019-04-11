package micronaut.modelmapper.demo

import org.modelmapper.Condition
import org.modelmapper.ModelMapper
import org.modelmapper.config.Configuration
import org.modelmapper.convention.NamingConventions
import org.modelmapper.spi.MappingContext

import java.util.stream.Collectors

class ObjectMapperUtils {

  private static ModelMapper modelMapper = new ModelMapper()

  static {
    modelMapper = new ModelMapper()
//    modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT)
    modelMapper.getConfiguration()
      .setFieldMatchingEnabled(true)
    .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE)
    .setSourceNamingConvention(NamingConventions.JAVABEANS_MUTATOR)
  }

  /**
   * Hide from public usage.
   */
  private ObjectMapperUtils() {
  }

  /**
   * <p>Note: outClass object must have default constructor with no arguments</p>
   *
   * @param <D>      type of result object.
   * @param <T>      type of source object to map from.
   * @param entity   entity that needs to be mapped.
   * @param outClass class of result object.
   * @return new object of <code>outClass</code> type.
   */
  static <D, T> D map(final T entity, Class<D> outClass) {
    return modelMapper.map(entity, outClass)
  }

  /**
   * <p>Note: outClass object must have default constructor with no arguments</p>
   *
   * @param entityList list of entities that needs to be mapped
   * @param outCLass   class of result list element
   * @param <D>        type of objects in result list
   * @param <T>        type of entity in <code>entityList</code>
   * @return list of mapped object with <code><D></code> type.
   */
  static <D, T> List<D> mapAll(final Collection<T> entityList, Class<D> outCLass) {
//    Type type = new TypeToken<List<D>>() {}.getType()
    return entityList.stream()
      .map({ entity -> map(entity, outCLass) })
      .collect(Collectors.toList())
//    return modelMapper.map(entityList, type)
  }

  /**
   * Maps {@code source} to {@code destination}.
   *
   * @param source      object to map from
   * @param destination object to map to
   */
//  static <S, D> D map(final S source, D destination) {
//    modelMapper.map(source, destination)
//    return destination
//  }



  /**
   * Creating a Mapper field skip condition
   *
   * @param skipFields list of string. For eg. ['courses', 'students']
   * @return {@link org.modelmapper.Condition}
   */
  static Condition createSkipCondition(skipFields) {
    new Condition() {
      boolean applies(MappingContext context) {
        !(context.mapping.destinationProperties[0].name in skipFields)
      }
    }
  }

  static createTypeMap(Class source, Class target, String typeMapName = null, def skipFields = null) {
    if (skipFields && typeMapName) {
      modelMapper.createTypeMap(source, target, typeMapName).propertyCondition = createSkipCondition(skipFields)
    } else if (typeMapName) {
      modelMapper.createTypeMap(source, target, typeMapName)
    } else if (skipFields) {
      modelMapper.createTypeMap(source, target).propertyCondition = createSkipCondition(skipFields)
    } else {
      modelMapper.createTypeMap(source, target)
    }
  }
}
