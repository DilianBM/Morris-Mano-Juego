/**
 * esta clase contiene los metodos para realizar el juego Morris
 * @author (Dilian) 
 * @version (1)
 */
import javax.swing.JOptionPane;
public class TableroMorris
{
    Controlador prueba= new Controlador();
    int numfilas,numcolumnas;
    int persona,totaljugadas;
    int col,fil;
    int colu,fila;
    char marca=' ';
    char marca2=' ';
    char tablero[][];
    int numpiezas=1;
    int numpiezas2=1;
    int cantcapturadas=0;
    int cantcapturadas2=0;
    char mat[][];
    /*
     * metodo constructor inicializa la matriz y pinta el tablero
     */
    public TableroMorris(int n){
        tablero=new char[n+2][n+2];// colchon
        mat=new char[n+2][n+2];
        for (int f=0;f<n;f++){
            for (int c=0;c<n;c++){// pinta el tablero para que se vea mas agradable
                tablero[3][3]=' ';
                tablero[0][c]='-';
                tablero[6][c]='-';
                if(f>=1&& f<6){
                    tablero[f][0]='|';
                    tablero[f][6]='|';
                    tablero[3][6]='-';
                    tablero[3][0]='-';
                }
                if(f>1&& f<5){
                    tablero[f][1]='|';
                    tablero[f][5]='|';
                    tablero[3][1]='-';
                    tablero[3][5]='-';
                }
                if(c>=1&& c<=5){
                    tablero[1][c]='-';
                    tablero[5][c]='-';
                    tablero[1][3]='|';
                    tablero[5][3]='|';
                }
                if(f>2&& f<4){
                    tablero[f][2]='|';
                    tablero[f][4]='|';

                }
                if(c>=2&& c<=4){
                    tablero[2][c]='-';
                    tablero[4][c]='-'; 
                    tablero[2][3]='|';
                    tablero[4][3]='|';
                }
            }
        }
        for(int c=0;c<n;c++){
            for(int f=0;f<n;f++){
                mat[f][c]=tablero[f][c];
            }
        }

        numfilas=n;
        numcolumnas=n;
    }

    /*
     * no recibe nada y devuelve una String con el tablero
     */
    public String muestreTab(){
        String t="";
        t="";
        for (int c=0;c<tablero.length-2;c++){
            t+=c+"\t";
        }
        t+="\n";

        for (int f=0;f<tablero.length;f++){
            for (int c=0;c<tablero[1].length;c++){
                t=t+tablero[f][c]+"\t"; 
            }
            t+="\n";
        }
        return t;
    }

    /* 
     * no recibe ni retorna nada, llama a los otros metodos y es el inicio del juego
     */
    public void jueguen(){
        boolean gano=false;
        // boolean puedeJugar=true;
        String hil =JOptionPane.showInputDialog("Con que marca desea jugar persona 1:");
        marca=hil.charAt(0);

        String hil2 =JOptionPane.showInputDialog("Con que marca desea jugar persona 2:");
        marca2=hil2.charAt(0);
        JOptionPane.showMessageDialog(null,"Cada jugador debe ingresar solo filas y columnas del o al 6");

        while (gano!=true && totaljugadas<50){ 
            do{
                juegueper1();
                System.out.println(muestreTab());
                if(cantcapturadas==7){
                    JOptionPane.showMessageDialog(null,"gano"+" "+ "persona1");
                    gano=true;
                    break;
                }
                seguirjugando();

                juegueper2();
                System.out.println(muestreTab());
                if(cantcapturadas2==7){
                    gano=true;
                    System.out.println("gano"+" "+ "persona2");
                    break;

                }
                seguirjugando();
                if(totaljugadas==50){
                    seguirjugando();
                }

            }while ((col<0)||(col>=7));
        }
    }

    /*
     * no recibe ni retorna nada y ve que la persona ingrese los datos y con estos llama a otros metodos para verificar ciertas condiciones
     */
    public void juegueper1(){

        boolean esmolino=false;
        if (numpiezas<=9){
            persona=1;
            col  =Integer.parseInt(JOptionPane.showInputDialog("juege persona 1 ingrese la columna"));
            fil =Integer.parseInt(JOptionPane.showInputDialog("juege persona 1 ingrese la fila"));
            boolean asig=false;
            asig=asigneficha(marca,persona);
            if(esmol(marca,fil,col)==true){
                System.out.println(muestreTab()); 
                borreficha2();
                cantcapturadas++;
            }

            if(asig==true){
                numpiezas++;
                totaljugadas++;
            }
        }
        else if(numpiezas>9){
            String campos[]=new String[4];
            int vec[]=new int[4];
            String entrada="";
            entrada=JOptionPane.showInputDialog("jugador 1 ingrese en el siguiente orden los datos \n filaAmover,columnaAmover,filaDestino,columnaDestino? Separados POR ,");
            campos =entrada.split(",");
            for(int i=0;i<4;i++){
                vec[i]=Integer.parseInt(campos[i]);//vector de filas y columnas
            }
            if(tablero[vec[0]][vec[1]]==marca && (tablero[vec[2]][vec[3]]!=marca && tablero[vec[2]][vec[3]]!=marca2) ){
                if(puedeMover(persona,vec[0],vec[1],vec[2],vec[3])==true){
                    tablero[vec[2]][vec[3]]=marca;
                    tablero[vec[0]][vec[1]]=mat[vec[0]][vec[1]];
                    if(esmol(marca,vec[2],vec[3])==true){
                        if(existeficha(marca2)==true){
                            System.out.println(muestreTab());
                            borreficha2();
                            cantcapturadas++;
                        }else{
                            JOptionPane.showMessageDialog(null,"todas las fichas de su enemigo se encuentran en molino no puede eliminar");
                            juegueper2();
                        }
                    }
                    totaljugadas++;
                }else{
                    juegueper1();
                }

            }else{
                juegueper1();
            }

        }

    }

    /*
     * no recibe ni retorna nada y ve que la persona2 ingrese los datos y con estos llama a otros metodos para verificar ciertas condiciones
     */
    public void juegueper2(){
        persona=2;
        int cantcapturadas2=0;
        if(numpiezas2<=9){
            try{
                col  =Integer.parseInt(JOptionPane.showInputDialog("juege persona 2 ingrese la columna"));
                fil  =Integer.parseInt(JOptionPane.showInputDialog("juege persona 2 ingrese la fila"));
            }catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null,"ERROR ingreso algo mal");

            }
            boolean asigna= false;
            asigna= asigneficha(marca2,persona);
            if(esmol(marca2,fil,col)==true){//controla que solo puede eliminar otra ficha si tengo un molino
                if(existeficha(marca)){//controla que si los dos tienen molino al mismo tiempo pase turno
                    System.out.println(muestreTab());
                    borreficha();
                    cantcapturadas2++;
                }else{
                    juegueper1();
                }
            }
            if(asigna==true){
                numpiezas2++;
                totaljugadas++;
            }

        }else{if(numpiezas2>9){//caso donde se empiezan a mover por el tablero
                int co  =Integer.parseInt(JOptionPane.showInputDialog("jugador 2 cual columna va a mover?"));
                int fi  =Integer.parseInt(JOptionPane.showInputDialog("jugador 2 cual fila va a mover?"));
                int colmmo=Integer.parseInt(JOptionPane.showInputDialog("juege persona 2 ingrese la columna a la que se desea mover"));
                int filmo =Integer.parseInt(JOptionPane.showInputDialog("juege persona 2 ingrese la fila a la que se desea mover"));

                if(tablero[fi][co]==marca2 && (tablero[filmo][colmmo]!=marca && tablero[filmo][colmmo]!=marca2) ){
                    if(puedeMover(persona,fi,co,filmo,colmmo)==true){
                        tablero[filmo][colmmo]=marca2;
                        tablero[fi][co]=mat[fi][co];
                        esmol(marca2,filmo,colmmo);
                        if(esmol(marca2,filmo,colmmo)==true){
                            if(existeficha(marca)){
                                System.out.println(muestreTab());
                                borreficha();
                                cantcapturadas2++;

                            }else{
                                juegueper1();
                            }
                        }
                        totaljugadas++;//contador para saber si se llega a 50 jugadas es global

                    }else{
                        juegueper2();
                    }

                }else{
                    juegueper2();
                }

            }
        }
    }

    /*
     * recibe la marca y la persona que esta jugando y evalua si puede colocar esa ficha y retorna un booleano 
     */
    public boolean asigneficha(char mar ,int persona){
        boolean asignado=false;  
        if(tablero[fil][col]!=' '&& asignado==false){
            if((fil==col|| fil+col==6 || col==3 || fil==3 )&& (tablero[fil][col]!=marca&&tablero[fil][col]!=marca2)){

                tablero[fil][col]=mar;
                asignado=true;

            }else{if(asignado==false){
                    JOptionPane.showMessageDialog(null,"En ese lugar no se puede meter una ficha ingrese otro lugar");
                    if (persona==1){
                        juegueper1();
                    }else if(persona==2){
                        juegueper2();

                    }
                }

            }
        }else{
            JOptionPane.showMessageDialog(null,"En ese lugar no se puede meter una ficha  ingrese otro lugar");
            if (persona==1){
                juegueper1();
            }else if(persona==2){
                juegueper2();

            }
        }

        return asignado;
    }

    /*
     * recibe la persona que esta jugando y la fila y columna que va a mover y a donde la va mover y
     * se encarga de ver si la persona puede moverse a un lugar de interseccion  y retorna un booleano que depende si puede o no moverse
     */
    public boolean puedeMover(int persona,int filamov,int colmov,int fildes,int colmdes){
        boolean puedemover=false;

        if((fildes==0 || fildes==6 ||fildes==3) &&( colmdes==3 || colmdes==0 || colmdes==6)){
            if((fildes==filamov  && colmdes==colmov+3) ||((fildes==filamov+3 ) &&(colmdes==colmov))||((fildes==filamov-3 ) &&(colmdes==colmov))  || ((fildes==filamov ) &&( colmdes==colmov-3))||((colmov==colmdes)&&(fildes==filamov+1)) ||((colmov==colmdes)&&(fildes==filamov-1))||((colmov==colmdes+1)&&(fildes==filamov)) ||((colmov==colmdes-1)&&(fildes==filamov))){
                puedemover = true;

            }

        }
        if((fildes==1 || fildes==3 ||fildes==5) &&( colmdes==1 || colmdes==3|| colmdes==5)){
            if((fildes==filamov  && colmdes==colmov+2) ||((fildes==filamov+2 ) &&(colmdes==colmov))||((fildes==filamov-2 ) &&(colmdes==colmov))  || ((fildes==filamov ) &&( colmdes==colmov-2))||((colmov==colmdes+1)&&(fildes==filamov)) ||((colmov==colmdes)&&(fildes==filamov-1))||((colmov==colmdes-1)&&(fildes==filamov)) ||((colmov==colmdes)&&(fildes==filamov+1))){
                puedemover = true;

            }

        }
        if((fildes==2 || fildes==3 ||fildes==4) &&( colmdes==2 || colmdes==3|| colmdes==4)){
            if((fildes==filamov  && colmdes==colmov+1) ||((fildes==filamov+1 ) &&(colmdes==colmov))||((fildes==filamov-1 ) &&(colmdes==colmov))  || ((fildes==filamov ) &&( colmdes==colmov-1))||((colmov==colmdes+1)&&(fildes==filamov)) ||((colmov==colmdes+1)&&(fildes==filamov))||((colmov==colmdes-1)&&(fildes==filamov)) ||((colmov==colmdes)&&(fildes==filamov+1))){
                puedemover = true;

            }
        }
        return puedemover;

    }

    /*
     * este metodo recibe la marca de la persona y la fila y columna que quiere borrar y verifica si esta se puede o no borrar y devuelve
     * un booleano 
     */
    public boolean puedeEliminar(char mar,int filaborrar,int colborrar){
        boolean puedeborrar=false;
        if(esmol(mar,filaborrar,colborrar)==false && tablero[filaborrar][filaborrar]==mar){
            puedeborrar=true;
        }

        return puedeborrar;
    }

    /*
     * metodo que se encarga de borrar la ficha de la persona2 pero si cumple con ciertas condiciones y no recibe ni retorna nada
     */
    public void borreficha2(){
        int c=Integer.parseInt(JOptionPane.showInputDialog("persona 1 ha hecho un MOLINO ingrese la columna  que  desea borrar"));
        int f =Integer.parseInt(JOptionPane.showInputDialog("persona 1 ha hecho un MOLINO ingrese la fila  que  desea borrar"));

        if(puedeEliminar(marca2,f,c)==true && tablero[f][c]==marca2){
            tablero[f][c]=mat[f][c];
        }else{
            borreficha2();

        }
    }

    /*
     * metodo que se encarga de borrar la ficha de la persona1 pero si cumple con ciertas condiciones y no recibe ni retorna nada
     */
    public void borreficha(){

        int c=Integer.parseInt(JOptionPane.showInputDialog("persona2 ha hecho un MOLINO ingrese la columna  que  desea borrar"));
        int f =Integer.parseInt(JOptionPane.showInputDialog(" persona2 ha hecho un MOLINO 2 ingrese la fila  que  desea borrar"));

        if(puedeEliminar(marca,f,c)==true && tablero[f][c]==marca){
            tablero[f][c]=mat[f][c];
        }else{
            borreficha();
        }
    }

    /*
     * este metodo es el que se encarga de preguntar si quieren reiniciar cuando quieran y no recibe ni retorna nada
     */
    public void seguirjugando(){
        try{
            int c=Integer.parseInt(JOptionPane.showInputDialog("Desea iniciar otra partida? 1.si 2.no"));
            if(c==1){
                Controlador.main (null);
                jueguen();
            }
        }catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,"ERROR ingreso algo mal");

        }
    }

    /*
     * este metodo recibe la marca del jugador y evalua si este tiene un molino y retorna un booleano
     */
    public boolean esmol(char mar,int fila,int col){
        boolean molino=false;
        int x=1;//este es el contador que sirve para ver en que caso se mete
        while( molino==false&& x<4 ){
            if(((fila-x>=0)&&(fila+x<=6))&& ((tablero[fila-x][col]==mar)&&(tablero[fila+x][col]==mar))){
                molino=true;
            }
            else if(((fila+x<=6)&&(col+(x*2)<=6))&&((tablero[fila+x][col]==mar)&&(tablero[fila+(x*2)][col]==mar))){
                molino=true;
            }
            else if(((fila-x>=0)&&(fila-(x*2)>=0))&&((tablero[fila-x][col]==mar)&&(tablero[fila-(x*2)][col]==mar))){
                molino=true;
            }
            if(((col-x>=0)&&(col+x<=6))&& ((tablero[fila][col-x]==mar)&&(tablero[fila][col+x]==mar))){
                molino=true;
            }
            else if(((col+x<=6)&&(col+(x*2)<=6))&&((tablero[fila][col+x]==mar)&&(tablero[fila][col+(x*2)]==mar))){
                molino=true;
            }
            else if(((col-x>=0)&&(col-(x*2)>=0))&&((tablero[fila][col-x]==mar)&&(tablero[fila][col-(x*2)]==mar))){
                molino=true;
            }
            if (molino==false){
                x++;
            }
        }

        return molino;
    }

    /*
     * este metodo recibe la marca del jugador contrario y ve si este tiene una ficha que no este en molino y retorna 
     * un booleano true si existe y false si no
     */
    public boolean existeficha(char mar){
        boolean existe=false;
        for(int f=0; f<7;f++){
            for(int c=0;c<7;c++){
                if(tablero[f][c]==mar){
                    if(esmol(mar,f,c)!=true){
                        existe=true;
                    }
                }

            }
        }
        return existe;
    }
}

