package ru.example.Magenta.util;

import ru.example.Magenta.model.City;
import ru.example.Magenta.model.Distance;
import ru.example.Magenta.repository.CityDAO;
import ru.example.Magenta.service.CityServiceImpl;
import ru.example.Magenta.service.TestExampleServiceImpl;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Test {

    private static City c;


    public static void main(String[] args) throws JAXBException {



   City c = new City();
   c.setName("1111");
   c.setLongitude(1.11);
   c.setLatitude(40.11);

   City z = new City();
   z.setName("222");
   z.setLatitude(50.11);
   z.setLongitude(20.00);


    Distance distance = new Distance();
    distance.setFromCity(c);
    distance.setToCity(z);
    distance.setDistance(200000);

    Distance distance2 = new Distance();
    distance2.setFromCity(z);
    distance2.setToCity(c);
    distance2.setDistance(200000);

    Lists lists = new Lists();
    lists.getValuesT().add(c);
    lists.getValuesT().add(z);
    lists.getValuesH().add(distance);
    lists.getValuesH().add(distance2);

   JAXBContext context = JAXBContext.newInstance(Lists.class,City.class,Distance.class);
   File file = new File("my.xml");
   Marshaller marshaller = context.createMarshaller();
   marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);
   marshaller.marshal(lists,file);

  Unmarshaller unmarshaller = context.createUnmarshaller();
  Lists lists1 = (Lists) unmarshaller.unmarshal(file);
        System.out.println(lists1);
        System.out.println(lists1.getValuesT().size());
        System.out.println(lists1.getValuesH().size());
  for (int i=0; i < lists1.getValuesH().size();i++){
      ;
      System.out.println(lists1.getValuesT().get(i) +"     " +i);

  }






    }
}
