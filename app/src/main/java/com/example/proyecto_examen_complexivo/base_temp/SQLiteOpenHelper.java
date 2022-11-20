package com.example.proyecto_examen_complexivo.base_temp;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import androidx.annotation.Nullable;



public class SQLiteOpenHelper extends android.database.sqlite.SQLiteOpenHelper {

    private static String NOMBRE_BD= "base_temp";
    private static int VERSION_BD= 1;


    private static String TABLA_CARRITO= "create table carrito (" +
            "idPrdocuto INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
            "nombreProducto text UNIQUE, " +
            "descripcionProducto text, " +
            "precioProducto double, " +
            "cantidadCompra int, " +
            "stock int, " +
            "imagenProducto text,"+
            "estado int,"+
            "id_producto)";


    private static String TABLA_USUARIO= "create table usuario (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
            "nombreUsuario text, " +
            "tipoUsuario text, " +
            "clave text, " +
            "estado text)"; //ESTADOS: REGISTRADO / POR REGISTAR

    private static String TABLA_DATOS_TARJETA= "create table datos_tarjeta (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
            "numeroTarjeta text, " +
            "mesVencimiento text, " +
            "anioVenciomiento text, " +
            "cvv text)";

    private static String TABLA_DATOS_CLIENTE= "create table datos_cliente (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
            "nombre text, " +
            "apellido text, " +
            "cedula text, " +
            "fecha_nac text, " +
            "email text, " +
            "telefono text, " +
            "direccion text)";


    public SQLiteOpenHelper(@Nullable Context context) {
        super(context, NOMBRE_BD, null, VERSION_BD);
    }

    @Override
    public void onCreate(SQLiteDatabase base) {

        base.execSQL(TABLA_USUARIO);
        base.execSQL(TABLA_DATOS_TARJETA);
        base.execSQL(TABLA_DATOS_CLIENTE);
    }

    //
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLA_CARRITO);
        sqLiteDatabase.execSQL(TABLA_CARRITO);
        sqLiteDatabase.execSQL(TABLA_USUARIO);
        sqLiteDatabase.execSQL(TABLA_DATOS_TARJETA);
        sqLiteDatabase.execSQL(TABLA_DATOS_CLIENTE);
    }

    public boolean agregarCarrito(int id_producto, String nombre, String descripcion, String precio, String cantidad, String img, int stock, boolean estado){
        SQLiteDatabase bd= getWritableDatabase();
        int tipo=0;
        if (estado!=false){
            tipo=1;
        }else {
            tipo=0;
        }
        if (bd!=null){
            try{
                //bd.execSQL("INSERT INTO carrito VALUES('"+id+"','"+cedula+"','"+nombre+"','"+apellido+"','"+telefono+"','"+email+"')");

                bd.execSQL("INSERT INTO carrito VALUES("+null+",'"+nombre+"','"+descripcion+"',"+precio+","+cantidad+","+stock+",'"+img+"', "+tipo+","+id_producto+")");
                System.out.println(" base creada"+ id_producto);
                bd.close();
                return true;
            }catch (SQLiteConstraintException e){
                return false;
            }

        }else{
            return false;
        }
    }

    public void editarCarrito(String nombre, String cantidadCompra){
        SQLiteDatabase bd= getWritableDatabase();
        bd.execSQL("UPDATE carrito SET cantidadCompra="+cantidadCompra+" WHERE nombreProducto='"+nombre+"'");
        bd.close();
    }

    public void eliminarCarrito(String name){

        SQLiteDatabase bd= getWritableDatabase();
        String sql=null;
        if (name == null) {
            sql= "DELETE FROM carrito";
        }else {
            sql= "DELETE FROM carrito WHERE nombreProducto='"+name+"'";
        }
        bd.execSQL(sql);
        bd.close();

    }


    ///// TABLA USUARIO ///////



    public void editarUsuario(String nombreUsuario, String contrasenia, String datoValidacion){
        SQLiteDatabase bd= getWritableDatabase();
        bd.execSQL("UPDATE usuario SET nombreUsuario='"+nombreUsuario+"', clave='"+contrasenia+"'"+" WHERE nombreUsuario= '"+datoValidacion+"'");
        bd.close();
    }

    ///// TABLA DATOS PAGO ///////

   public boolean agregarDatosPago(String numeroTarjeta, String mesVencimiento, String anioVencimiento, String cvv) {
       SQLiteDatabase bd = getWritableDatabase();

       if (bd != null) {
           try {
               bd.execSQL("INSERT INTO datos_tarjeta VALUES(" + null + ",'" + numeroTarjeta + "','" + mesVencimiento + "','"+ anioVencimiento + "','" + cvv + "')");
               bd.close();

               return true;
           } catch (SQLiteConstraintException e) {
               return false;
           }

       } else {
           return false;
       }
   }

    public void eliminarDatosPago(String numTarjeta){

        SQLiteDatabase bd= getWritableDatabase();
        String sql=null;
        sql= "DELETE FROM datos_tarjeta";
        bd.execSQL(sql);
        bd.close();

    }

    ///// TABLA DATOS CLIENTE ///////

    public boolean agregarDatosCliente(String nombre, String apellido, String cedula, String fecha_nac, String email, String telefono, String direccion) {
        SQLiteDatabase bd = getWritableDatabase();

        if (bd != null) {
            try {
                //bd.execSQL("INSERT INTO carrito VALUES('"+id+"','"+cedula+"','"+nombre+"','"+apellido+"','"+telefono+"','"+email+"')");
                bd.execSQL("INSERT INTO datos_cliente VALUES("+null+",'"+nombre+"','"+apellido+"','"+cedula+"','"+fecha_nac+"','"+email+"','"+telefono+"','"+direccion+"')");
                bd.close();
                return true;
            } catch (SQLiteConstraintException e) {
                return false;
            }

        } else {
            return false;
        }
    }



    /*public List<Carrito> listar() {

        String sql = "SELECT * FROM carrito";
        Cursor cursor = query(sql);
        while (cursor.moveToNext()) {
            Carrito car = new Carrito();
            car.setId_carrito(cursor.getInt(0));
            car.setNombreProducto(cursor.getString(1));
            car.setDescripcionProducto(cursor.getString(2));
            car.setPrecioProducto(cursor.getDouble(3));
            car.setCatidadProducto(cursor.getInt(4));
            car.setStock(cursor.getInt(5));
            car.setImgProducto(cursor.getString(6));
            car.setId_producto(cursor.getInt(8));
            System.out.println(cursor.getInt(8)+ "  pppppppppppppp");
            listaCarrito.add(car);
        }
        return listaCarrito;
    }*/




}
