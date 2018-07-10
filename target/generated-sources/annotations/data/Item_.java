package data;

import data.User;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-08T09:00:07")
@StaticMetamodel(Item.class)
public class Item_ { 

    public static volatile SingularAttribute<Item, String> note;
    public static volatile SingularAttribute<Item, User> author;
    public static volatile SingularAttribute<Item, Integer> count;
    public static volatile SingularAttribute<Item, Boolean> checked;
    public static volatile SingularAttribute<Item, Long> id;
    public static volatile SingularAttribute<Item, String> title;
    public static volatile SingularAttribute<Item, String> url;

}