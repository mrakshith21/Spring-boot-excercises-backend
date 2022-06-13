package com.excercises.excercisesbackend.controller;

import com.excercises.excercisesbackend.models.Text;
import com.excercises.excercisesbackend.models.WordCount;
import com.excercises.excercisesbackend.models.ZodiacSign;
import com.excercises.excercisesbackend.service.Service;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.nio.file.Files;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.time.LocalDateTime;

@RestController
@CrossOrigin
public class Controller {

    int counter1 = 0, counter2;

    @Autowired
    private Service service;

    // 1
    @GetMapping("/welcome")
    public String welcome(@RequestParam ("name") String name){
        return "Welcome " + name;
    }

    // 2
    @GetMapping("/gender")
    public String gender(@RequestParam("gender") String gender){
        return gender + " checked";
    }

    // 3
    @GetMapping("/city")
    public String city(@RequestParam("city") String city){
        return "Selected city : " + city;
    }


    // 4
    @GetMapping("/skills")
    public String skills(@RequestParam Map<String, String> skills){
        StringBuilder response = new StringBuilder();
        for(String s : skills.keySet()){
            response.append(skills.get(s) + " ");
        }
        return response.append(" selected").toString();
    }

    // 5
    @GetMapping("/number")
    public ResponseEntity number(@RequestParam("number") String number){
        int num;
        try {
            num = Integer.parseInt(number);
            if(num < 0 || num > 9){
                return ResponseEntity.badRequest().build();
            }
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
        String arr[] = {"Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
        return ResponseEntity.ok(arr[num]);
    }

    // 6
    @CrossOrigin
    @GetMapping("/word")
    public ResponseEntity word(@RequestParam("number") String number){
        int num;
        try {
            num = Integer.parseInt(number);
            if(num < 0 || num > 9){
                return ResponseEntity.badRequest().build();
            }
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
        String arr[] = {"Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
        return ResponseEntity.ok(arr[num]);
    }

    // 7
    @GetMapping(value = "/sentence")
    public List<String> sentence(@RequestParam("text") String text){
        return Arrays.asList(text.split(" "));
    }

    //  8
    @GetMapping(value = "/wordcount")
    public WordCount wordCount(@RequestParam("text") String text){
        Map<String, Integer> freq = new HashMap<>();
        for(String s : text.split(" ")){
            freq.put(s, freq.getOrDefault(s, 0) + 1);
        }
        return new WordCount(freq);

    }

    // 9
    @GetMapping(value = "/mulTable")
    public List<Integer> multiplicationTable(@RequestParam("number") String number){
        int num = Integer.parseInt(number);
        List<Integer> tables = new ArrayList<>();
        for(int i = 1; i <= 10; i++){
            tables.add(i * num);
        }
        return tables;
    }

    // 10
    @GetMapping(value = "/monuments", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getMonumentImage(@RequestParam("name") String name) throws IOException {

        var imgFile = new File("src/main/resources/static/images/monuments/" + name + ".jpg");

        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(Base64.getEncoder().encode(Files.readAllBytes(imgFile.toPath())));
    }

    // 11
    @GetMapping(value = "displayNumbers")
    public List<Integer> displayNumbers(@RequestParam("number") String number){
        int num = Integer.parseInt(number);
        List<Integer> nums = new ArrayList<>();
        for(int i = 1; i <= num; i++){
            nums.add(i );
        }
        return nums;
    }

    // 12
    @GetMapping(value = "/add")
    public Integer add(@RequestParam("num1") String num1, @RequestParam("num2") String num2){
        int op1 = Integer.parseInt(num1);
        int op2 = Integer.parseInt(num2);
        int result = op1 + op2;
        return result;
    }

    // 13, 14
    @GetMapping(value = "/calculator")
    public ResponseEntity<Integer> calculator(@RequestParam("num1") String num1, @RequestParam("num2") String num2, @RequestParam("operand") String operand){
        int op1 = Integer.parseInt(num1);
        int op2 = Integer.parseInt(num2);
        int result = 0;
        switch (operand){
            case "1" : result = op1 + op2; break;
            case "2" : result = op1 - op2; break;
            case "3" : result = op1 * op2; break;
            case "4" : if(op2 == 0) return ResponseEntity.badRequest().build();  result = op1 / op2; break;
            case "5" : if(op2 == 0) return ResponseEntity.badRequest().build();  result = op1 % op2; break;
            default: return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(result);
    }

    // 15 - counter
    @GetMapping(value = "counter")
    public Integer counter(){
        counter1++;
        return counter1;
    }

    // 16 - increment, decrement and reset
    @GetMapping(value = "incDecReset")
    public Integer incDecReset(@RequestParam("change") String change){
        switch (change) {
            case "increment" -> counter2++;
            case "decrement" -> counter2--;
            case "reset" -> counter2 = 0;
        }
        return counter2;
    }

    // 17 - zodiac sign
    @GetMapping(value = "/zodiacSign")
    public String zodiacSign(@RequestParam("date") String day, @RequestParam("month") String month) throws Exception {
        int d = Integer.parseInt(day);
        int m = Integer.parseInt(month);
        String zodiac = service.zodiacSign(d, m);
        if(zodiac.equals("None")){
            throw new Exception();
        }
        return zodiac;
    }

    @CrossOrigin()
    @GetMapping(value = "/zodiacSignImage", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> zodiacSign(@RequestParam("zodiac") String zodiac) throws IOException {
        var imgFile = new File("src/main/resources/static/images/zodiac_signs/" + zodiac.toLowerCase() + ".jpg");

        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(Base64.getEncoder().encode(Files.readAllBytes(imgFile.toPath())));
    }

    // 18
    @GetMapping(value = "/itemsDisplay")
    public ResponseEntity<List<String>> getItemsImages(@RequestParam("product") String product) throws IOException {

        var imgFile = new File("src/main/resources/static/images/product/" + product);
        List<String> products = new ArrayList<>();
        for(File image : Objects.requireNonNull(imgFile.listFiles())){
            products.add(image.getName().substring(0, image.getName().indexOf('.')));
        }

        return ResponseEntity
                .ok()
                .body(products);
    }

    @GetMapping(value = "/itemsDisplay/products/{product}/{image}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getItemsImages(@PathVariable("product") String product, @PathVariable("image") String image) throws IOException {

        var imgFile = new File("src/main/resources/static/images/product/" + product + "/" + image + ".jpg");


        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(Base64.getEncoder().encode(Files.readAllBytes(imgFile.toPath())));
    }


    // 19 - current date
    @GetMapping(value = "/date")
    public String getDate(){
        return LocalDate.now().toString();
    }

    // 20 - current time
    @GetMapping(value = "/time")
    public String getTime(){
        return LocalTime.now().toString();
    }

}
