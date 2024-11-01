//import java.awt.Choice;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;   // or import java.util.*;

class Car{
    private String carno;
    private String Brand;
    private String Model;
    private double PricePerDay;
    private boolean isAvailable;

    public Car(String Carno,String Brand,String Model,double PricePerDay){
        this.carno=Carno;
        this.Brand=Brand;
        this.Model=Model;
        this.PricePerDay=PricePerDay;
        this.isAvailable=true;

    }
    public String getcarno(){
        return carno;
    }
    public String getcarbrand(){
        return Brand;
    }
    public String getcarmodel(){
        return Model;
    }
    public double getprice(int NoOfDays){
        return PricePerDay*NoOfDays;
    }
    public boolean isAvailable(){
        return isAvailable;
    }
    public void rent(){
        isAvailable=false;
    }
    public void returnCar(){
        isAvailable=true;
    }
}

class Customer{
    private String CustomerId;
    private String name;

    public Customer(String id,String name){
        this.CustomerId=id;
        this.name=name;
    }
    public String getCustomerId(){
        return CustomerId;
    }
    public String getCustomerName(){
        return name;
    }

}

class Rent{
    private Car car;
    private Customer customer;
    private int Days;

    public Rent(Car car,Customer client,int days){
        this.car=car;
        this.customer=client;
        this.Days=days;
    }
    public Car getCar(){
        return car;
    }
    public Customer getCustomer(){
        return customer;
    }
    public int getDays(){
        return Days;
    }
}

class CarRentalSystem{
    private List<Car> cars;
    private List<Customer> customers;
    private List<Rent> rentals;

    public CarRentalSystem(){
        cars=new ArrayList<>();
        customers=new ArrayList<>();
        rentals=new ArrayList<>();
    }
    public void addCar(Car car){
        cars.add(car);
    }
    public void addCustomer(Customer customer){
        customers.add(customer);
    }
    public void rentcar(Car car,Customer customer,int Days){
        if(car.isAvailable()){
            car.rent();
            rentals.add(new Rent(car, customer, Days));
        }else{
            System.out.println("We Extremly Sorry Sir/Madam ! ,Car is Not Available now for Rent");
        }
    }
    public void returnCar(Car car){
        car.returnCar();
        Rent rentalToRemove =null;
        for(Rent rental : rentals){
            if(rental.getCar()==car){
                rentalToRemove=rental;
                break;
            }
        }
        if(rentalToRemove !=null){
            rentals.remove(rentalToRemove);
        }
        else{
            System.out.println("Car Was Not Rented !");
        }
    }

    public void menu(){
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.println("============Car Renatl System===========");
            System.out.println("1. Rent a Car");
            System.out.println("2. Return a Car");
            System.out.println("3. Exit !");
            System.out.println("============Car Renatl System===========");
            System.out.println("Select the Above Option");

            int choice =sc.nextInt();
            sc.nextLine();
            
            if(choice==1){
                System.out.println("========Rent a Car=========");
                System.out.println("Enter your Name :");
                String name=sc.nextLine();

                System.out.println("Availale Cars :");

                for(Car car :cars){
                    if(car.isAvailable()){
                        System.out.println(car.getcarno() +"--"+car.getcarbrand()+"--"+car.getcarmodel());
                    }
                }
            
            System.out.println("Enter the Car Number you Want To Rent :");
            String carid=sc.nextLine();

            System.out.println("How Many Days you Want to Rent ? :");
            int rentaldays=sc.nextInt();
            sc.nextLine();

            Customer newCustomer=new Customer("CUST"+(customers.size()+1),name);
            addCustomer(newCustomer);

            Car selectedCar =null ;
            for(Car car : cars){
                if(car.getcarno().equals(carid)&&car.isAvailable()){
                    selectedCar=car;
                    break;
                }
            }

            if(selectedCar != null){
                double totalprice=selectedCar.getprice(rentaldays);
                System.out.println("======RENTAL INFORMATION======");
                System.out.println("Your Bill :");
                System.out.println("Customer Id:"+newCustomer.getCustomerId());
                System.out.println("Customer Name:"+newCustomer.getCustomerName());
                System.out.println("Car  :"+selectedCar.getcarbrand() +"--"+selectedCar.getcarmodel());
                System.out.println("Rental Days: "+ rentaldays);
                System.out.println("Total Price: $ %.2f%n"+totalprice);

                System.out.println("Confirmation !!!,Conform renatl (Yes/No) : ?");
                String conform=sc.nextLine();
                if(conform.equalsIgnoreCase("Yes")){
                    rentcar(selectedCar, newCustomer, rentaldays);
                    System.out.println("Car Rented Successfully !!");
                }else{
                    System.out.println("Rent Cancelled");

                }


                
            }else{
                System.out.println("Invalid Selection or car is not Available");
            }
            
        }else if(choice ==2){
            System.out.println("=====Return a Car====");
            System.out.println("Enter the Car Number you want to return :");
            String carid=sc.nextLine();
            Car carToReturn = null;
            for(Car car : cars){
                if(car.getcarno().equals(carid)){
                    carToReturn = car;
                    break;
                }
            }
            if(carToReturn != null){
                Customer customer = null;
                for(Rent rental: rentals){
                    if(rental.getCar()==carToReturn){
                        customer=rental.getCustomer();
                        break;
                    }
                }
                if(customer != null){
                    returnCar(carToReturn);
                    System.out.println("Car Rented Successfully by "+customer.getCustomerName());
                }else{
                    System.out.println("Car was not rented or rental information is Missing !!");
                }
            }else{
                System.out.println("Invalid car Id ");
            }


        }else if(choice ==3){
            break;
        }else{
            System.out.println("Invalid Choice !! Please Enter Valid Option or Choice");
        }

        }
        System.out.println("\n Thank you for using the Car Rental System !!");
    }
}
public class main{
    public static void main(String[] args) {
        CarRentalSystem rentalSystem = new CarRentalSystem();

        Car car1 = new Car("C1032", "Toyoto", "Camry", 500.0);
        Car car2 = new Car("C1042", "Honda", "Accord", 600.0);
        Car car3 = new Car("C1052", "Mahendra", "Thar", 700.0);

        rentalSystem.addCar(car1);
        rentalSystem.addCar(car2);
         rentalSystem.addCar(car3);
         rentalSystem.menu();
    }
}