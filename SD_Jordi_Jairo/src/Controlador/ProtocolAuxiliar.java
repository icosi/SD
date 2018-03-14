/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;

/**
 *
 * @author Jairo
 */
public class ProtocolAuxiliar {
    
    public void send_UID(ComUtils utils,Controller control) {
        try {
            // Sending stack
            utils.write_userId();
            utils.write_space();
            utils.write_int32(control.getClient().getUID());
            
        } catch (Exception e) {
            System.out.println("Unable to write.");
        }
    }
    
    public void send_PLAY(ComUtils utils,Controller control) {
        try {
            // Sending stack
            utils.write_play();
            
        } catch (Exception e) {
            System.out.println("Unable to write.");
        }
    }
    
    public void send_STOP(ComUtils utils,Controller control) {
        try {
            // Sending stack
            utils.write_stop();
            
        } catch (Exception e) {
            System.out.println("Unable to write.");
        }
    }
    
    public void send_BET(ComUtils utils,Controller control) {
        try {
            // Sending stack
            utils.write_bet();
            
        } catch (Exception e) {
            System.out.println("Unable to write.");
        }
    }
    
    public void send_CALL(ComUtils utils,Controller control) {
        try {
            // Sending stack
            utils.write_call();
            
        } catch (Exception e) {
            System.out.println("Unable to write.");
        }
    }
    
    public void send_FOLD(ComUtils utils,Controller control) {
        try {
            // Sending stack
            utils.write_fold();
            
        } catch (Exception e) {
            System.out.println("Unable to write.");
        }
    }
    
    public void send_CHECK(ComUtils utils,Controller control) {
        try {
            // Sending stack
            utils.write_check();
            
        } catch (Exception e) {
            System.out.println("Unable to write.");
        }
    }
    
    public void send_SHOW(ComUtils utils,Controller control) {
        try {
            // Sending stack
            utils.write_show();
            
        } catch (Exception e) {
            System.out.println("Unable to write.");
        }
    }
    
    public int receive_STACK(ComUtils utils) {
        String cmd_received = "";
        int int_received= -1;
        while (cmd_received != "STK") {
            try {
                cmd_received = utils.read_command3();
                if (cmd_received == "STK") {
                    continue;
                } else {
                    utils.write_error();
                    utils.write_space();
                    utils.write_error_num(02);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        while (cmd_received != " ") {
            try {
                cmd_received = utils.read_space();
                if (cmd_received == " ") {
                    continue;
                } else {
                    utils.write_error();
                    utils.write_space();
                    utils.write_error_num(02);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        while(int_received == -1 ){
            try {
                int_received = utils.read_int32();
                if (int_received != -1) {
                    continue;
                } else {
                    utils.write_error();
                    utils.write_space();
                    utils.write_error_num(02);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        while (cmd_received != " ") {
            try {
                cmd_received = utils.read_space();
                if (cmd_received == " ") {
                    continue;
                } else {
                    utils.write_error();
                    utils.write_space();
                    utils.write_error_num(02);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        while(int_received == -1 ){
            try {
                int_received = utils.read_int32();
                if (int_received != -1) {
                    continue;
                } else {
                    utils.write_error();
                    utils.write_space();
                    utils.write_error_num(02);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        return 1;
    }
    
    public String receive_TURN(ComUtils utils) {
        String cmd_received = "";
        
        while (cmd_received != "STR") {
            try {
                cmd_received = utils.read_command3();
                if (cmd_received == "STR") {
                    continue;
                } else {
                    utils.write_error();
                    utils.write_space();
                    utils.write_error_num(02);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        while (cmd_received != " ") {
            try {
                cmd_received = utils.read_space();
                if (cmd_received == " ") {
                    continue;
                } else {
                    utils.write_error();
                    utils.write_space();
                    utils.write_error_num(02);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        while (cmd_received !="C" || cmd_received != "S") {//TODO quizas se tenga que cambiar por los ==
            try {
                cmd_received = utils.read_command3();
                if (cmd_received == "C" || cmd_received == "S" ) {
                    continue;
                } else {
                    utils.write_error();
                    utils.write_space();
                    utils.write_error_num(02);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        
        return cmd_received;
    }
    
    public int receive_BET(ComUtils utils) {
        String cmd_received = "";
        while (cmd_received != "BET") {
            try {
                cmd_received = utils.read_command3();
                if (cmd_received == "BET") {
                    continue;
                } else {
                    utils.write_error();
                    utils.write_space();
                    utils.write_error_num(02);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return 1;
    }
    
    public int receive_CARD(ComUtils utils) {
        String cmd_received = "";
        while (cmd_received != "CRD") {
            try {
                cmd_received = utils.read_command3();
                if (cmd_received == "CRD") {
                    continue;
                } else {
                    utils.write_error();
                    utils.write_space();
                    utils.write_error_num(02);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        while (cmd_received != " ") {
            try {
                cmd_received = utils.read_space();
                if (cmd_received == " ") {
                    continue;
                } else {
                    utils.write_error();
                    utils.write_space();
                    utils.write_error_num(02);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        while (cmd_received !="J" || cmd_received != "Q" || cmd_received != "K") {//TODO quizas se tenga que cambiar por los ==
            try {
                cmd_received = utils.read_card();
                if (cmd_received == "J" || cmd_received == "Q" || cmd_received == "K") {
                    continue;
                } else {
                    utils.write_error();
                    utils.write_space();
                    utils.write_error_num(02);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return 1;
    }
    
    public int receive_WIN(ComUtils utils) {
        String cmd_received = "";
        while (cmd_received != "WIN") {
            try {
                cmd_received = utils.read_command3();
                if (cmd_received == "WIN") {
                    continue;
                } else {
                    utils.write_error();
                    utils.write_space();
                    utils.write_error_num(02);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        while (cmd_received != " ") {
            try {
                cmd_received = utils.read_space();
                if (cmd_received == " ") {
                    continue;
                } else {
                    utils.write_error();
                    utils.write_space();
                    utils.write_error_num(02);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        while (cmd_received !="C" || cmd_received != "S") {//TODO quizas se tenga que cambiar por los ==
            try {
                cmd_received = utils.read_command3();
                if (cmd_received == "C" || cmd_received == "S" ) {
                    continue;
                } else {
                    utils.write_error();
                    utils.write_space();
                    utils.write_error_num(02);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        
        return 1;
    }
    
    public int receive_CALL(ComUtils utils) {
        String cmd_received = "";
        while (cmd_received != "CAL") {
            try {
                cmd_received = utils.read_command3();
                if (cmd_received == "CAL") {
                    continue;
                } else {
                    utils.write_error();
                    utils.write_space();
                    utils.write_error_num(02);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return 1;
    }
    
    public int receive_CHECK(ComUtils utils) {
        String cmd_received = "";
        while (cmd_received != "CHK") {
            try {
                cmd_received = utils.read_command3();
                if (cmd_received == "CHK") {
                    continue;
                } else {
                    utils.write_error();
                    utils.write_space();
                    utils.write_error_num(02);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return 1;
    }
    
    public int receive_FOLD(ComUtils utils) {
        
        return 1;
    }
    
    public int receive_SHOW(ComUtils utils) {
        
        return 1;
    }
    
}
