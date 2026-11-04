package rolea;

import java.util.Scanner;

public class TestRoleA {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            QuanLySanPham.menu(scanner);
        }
    }
}
