package com.example.springexample.test;

import com.example.springexample.dto.AuthorDto;
import com.example.springexample.dto.lib.Dto;
import com.example.springexample.dto.lib.DtoIdMixin;
import com.example.springexample.entity.Author;
import com.example.springexample.repository.AuthorRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.repository.CrudRepository;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class TestEntity<N, T extends Dto, U extends CrudRepository<N, Long>> {
    private File mainDataFile;
    private File mapperFile;
    private String dtoClassName;
    private Class<T> dtoClass;
    private U repository;
    private Map<String, String> mapping = new HashMap<>();
    private ObjectMapper mapper;
    private ObjectMapper dtoMapper;
    private Map<String, List<T>> data = new HashMap<>();

    public TestEntity(String pathToResource, @NotNull Class<N> entity, @NotNull Class<T> dtoClass, U repository) throws URISyntaxException, IOException {
        this.mapper = new ObjectMapper();
        this.dtoMapper = new ObjectMapper().addMixIn(dtoClass, DtoIdMixin.class);
        this.dtoClass = dtoClass;
        this.repository = repository;
        pathToResource = Optional.ofNullable(pathToResource).orElse("/test_data/data.json");
        String pathToMapper = "/test_data/"+dtoClass.getSimpleName()+".json";
        this.mainDataFile = new File(Paths.get(getClass().getResource(pathToResource).toURI()).toRealPath().toString());
        this.mapperFile = new File(this.mainDataFile.toPath().toRealPath().toString()+"_");
        this.dtoClassName = this.dtoClass.getSimpleName();
        //int lastIndex = dtoClassName.lastIndexOf('.');
        //this.dtoClassName = this.dtoClassName.substring(lastIndex + 1);
        ObjectMapper jsonMapper = new ObjectMapper();
        JsonNode rootNode = jsonMapper.readTree(mainDataFile);
        JsonNode targetNode = rootNode.path(dtoClassName);
        if (targetNode.isMissingNode()) {
            throw new IllegalStateException(String.format("Файл %s не содержит данные в узле %s",mainDataFile.getName(), dtoClass));
        }
        Map<String, List<Map<String, String>>> rows = jsonMapper.readValue(targetNode.toString(), Map.class);
        // преобразовал List<Map<String, String>> в List<T>
        data = rows.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue().stream().map(
                v ->  {
                    T dto = null;
                    try {
                        Constructor<T> constructor = dtoClass.getConstructor();
                        dto = constructor.newInstance();
                        dto.fillFromMap(v);
                    } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                    return dto;
                }
                ).collect(Collectors.toList())));
    }




    public static void main(String[] args) throws URISyntaxException, IOException {
        AuthorRepository authorRepository = null;
        TestEntity<? extends Author, AuthorDto, CrudRepository<Author, Long>> testEntity = new TestEntity<>(null, Author.class, AuthorDto.class, authorRepository);
    }

    public void clearMapping() {
        mapping.clear();
    };

    public void addMapping(String oldId, String newId) {
        mapping.put(oldId, newId);
    }
    public void writeMapperToFile() throws IOException {
        mapper.writeValue(mapperFile, mapping);
    }

    public String getOldId(T dto) throws JsonProcessingException {
        JsonNode inJson = mapper.readTree(mapper.writeValueAsString(dto));
        return inJson.get("id").asText();
    }

    public String getNewId(String outContent) throws JsonProcessingException {
        return mapper.readTree(outContent).get("id").asText();
    }

    public String makeInContent(T dto) throws JsonProcessingException {
        return dtoMapper.writeValueAsString(dto);
    }

    private Map<String, String> getMapping() {
        return mapping;
    }

    public Map<String, List<T>> getData() {
        return data;
    }
}
