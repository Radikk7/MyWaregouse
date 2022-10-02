package com.example.mywaregouse.service;

import com.example.mywaregouse.models.Product;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FactoryTest {

    @AfterEach
    void tearDown() {
    }

    @Test
    void productException() throws IOException {
        List<String> lines = Files.readAllLines(new File("product111.txt").toPath(), Charset.defaultCharset() );
        for (String i:lines) {
            Factory.productException(i);
            Assertions.assertNotNull(i);
        }
    }
}