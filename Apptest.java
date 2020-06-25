/Î±´úÂë/

/.../
package dezhoupuke;

import ...

class Apptest {
       @Test
          void appHasAGreeting(){
                 App classUnderTest = new App();
asserNull(classUnderTest.getGreeting(), message "app should have a greeting");
}

      @Test
        void shouldReturn"White wins"WhenGiveBlack("2 H 3 D 5 S 9 C K D")White("2 C 3 H 4 S 8 C A H")()
       {
                Dezhoupuke dezhoupuke = new Dezhoupuke();
                Integer expectedResult = Dezhoupuke.getValueByIndex(Black("2 H 3 D 5 S 9 C K D")White("2 C 3 H 4 S 8 C A H"));
                Assertions.asserEquals( expected:"White wins", expectedResult);
        }

@Test
        void shouldReturn"White wins"WhenGiveBlack("2 H 4 S 4 C 2 D 4 H")White("2 S 8 S A S Q S 3 S")()
       {
                Dezhoupuke dezhoupuke = new Dezhoupuke();
                Integer expectedResult = Dezhoupuke.getValueByIndex(Black("2 H 4 S 4 C 2 D 4 H")White("2 S 8 S A S Q S 3 S"));
                Assertions.asserEquals( expected:"White wins", expectedResult);
        }
@Test
        void shouldReturn"White wins"WhenGiveBlack("2 H 3 H 5 H 9 H K H")White("2 C 3 H 4 S 5 C 6 H")()
       {
                Dezhoupuke dezhoupuke = new Dezhoupuke();
                Integer expectedResult = Dezhoupuke.getValueByIndex(Black("2 H 3 H 5 H 9 H K H")White("2 C 3 H 4 S 5 C 6 H"));
                Assertions.asserEquals( expected:"Black wins", expectedResult);
        }

@Test
        void shouldReturn"Tie"WhenGiveBlack(" 2 H 3 D 5 S 9 C K D")White("2 C 3 H 4 S 8 C A H")()
       {
                Dezhoupuke dezhoupuke = new Dezhoupuke();
                Integer expectedResult = Dezhoupuke.getValueByIndex(Black(" 2 H 3 D 5 S 9 C K D")White("2 C 3 H 4 S 8 C A H"));
                Assertions.asserEquals( expected:"Tie", expectedResult);
        }

}