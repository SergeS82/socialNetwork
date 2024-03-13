package com.example.springexample.dto.lib;

import java.util.Map;

public interface Dto<T extends Dto> {
    T fillFromMap(Map<String, String> map);
}
