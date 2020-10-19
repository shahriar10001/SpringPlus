package RepoUtility;

import org.modelmapper.ModelMapper;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ConvertService {

    public static <T> T convertObject(Object obj, Class<T> classOfT) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(obj, classOfT);
    }

    public static <D, T> List<D> convertList(final Collection<T> sourceList, Class<D> destination) {
        return sourceList.stream()
                .map(entity -> convertObject(entity, destination))
                .collect(Collectors.toList());
    }
}
