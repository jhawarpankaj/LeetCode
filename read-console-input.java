/*
4 5
1 2 10
2 3 15
1 3 5
4 2 2
4 3 40

*/

/*
nexInt() : read the next integer token and the cursor stays at the end of that token.
next() : read the next String token and the cursor stays at the end of that token.
likewise, nextDouble(), nextBoolean() etc.

nextLine() : reads the complete line from the current cursor position till the end of the line and the cursor goes to the 
beginning of the next line. (note that it will also read the empty string, in below scenario)

4 5
1 2 10

Scanner sc = new Scanner(System.in);
int m = sc.nextInt(); // reads 4 and cursor stays at the end of 4. 
int n = sc.nextInt(); // reads 5 and cursor stays at the end of 5.
String s = sc.nextLine(); // reads empty string from the end of 5 and cursor moves to next line.
String s = sc.nextLine(); // reads the complete line "1 2 10" and cursor moves to the beginning of next line.
*/

// Approach 1
Scanner sc = new Scanner(System.in);
int m = sc.nextInt();
int n = sc.nextInt();
for(int i = 1; i <= n; i++) {
  int a = sc.nextInt();
  int b = sc.nextInt();
  int c = sc.nextInt();
  System.out.println("a: " + a + ", b: " + b + ", c: " + c);
}
sc.close();

// Approach 2
Scanner sc = new Scanner(System.in);
int m = sc.nextInt();
int n = sc.nextInt();
sc.nextLine(); // this is important to get the cursor to the next line.
while(sc.hasNextLine()) {
  String s = sc.nextLine();
  // it's important to trim to remove any leading/trailing spaces plus the regex takes care of multiple spaces in between.
  String[] line = s.trim().split("\\s+"); 
  int a = Integer.parseInt(line[0]);
  int b = Integer.parseInt(line[1]);
  int c = Integer.parseInt(line[2]);
  System.out.println("a: " + a + ", b: " + b + ", c: " + c);
}
sc.close();
