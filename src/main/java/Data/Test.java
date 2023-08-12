package Data;

import java.util.ArrayList;


//            up_select.setVisible(true);
//                    add_btn_me.setVisible(false);
//                    idMe.setText(getData.medicine.getIdMe());
//                    nameMe.setText(getData.medicine.getNameMe());
//                    location.setText(getData.medicine.getLocation());
//                    price.setText(String.valueOf(getData.medicine.getPrice()));
//                    note.setText(getData.medicine.getNote());
//                    quantity.setText(String.valueOf(getData.medicine.getQuantity()));
//                    id_up_supme.setText(getData.medicine.getIdSup());
//                    image = new Image(getData.medicine.getImage().toString(),167,190,false,true);
//                    image_Me.setImage(image);
//                    getData.path = getData.medicine.getImage();
public class Test {
    public static void main(String[] args){
        ArrayList<A> list = new ArrayList<>();
        for(int i=0;i<4;i++){
            list.add(new A());
        }
        for (A b: list){
            System.out.println(b.toString());
        }
        int c =20;
        for(A b: list){
            if(b.getA()<=c){

                c=c-b.getA();
                b.setA(0);
                System.out.println("C:"+c);
            }else{
                b.setA(b.setAA(c));
                break;
            }
        }
        for (A b: list){
            System.out.println(b.toString());
        }
    }
}
 class A {
    int a =10;
    A(){

    }

     public void setA(int c) {
         this.a = c;
     }
     public int setAA(int c) {
         this.a = this.a -c;
         return a;
     }

     public int getA() {
         return a;
     }

     @Override
     public String toString() {
         System.out.println(this.a);
         return null;
     }
 }
