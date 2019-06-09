package string;

/**
 * aaaabcca -> a4b1c2a1
 * [0, slow - 1] to keep
 * [f…] to explore
 * 
 * for each step:
 * fstart = f
 * while array[fstart] == array[f], f++
 * now a[fstart] != a[f]
 * compress [fstart, f-1]
 * array[slow] = array[fstart], slow++
 * array[slow + 1] = length = f - fstart (if length > 1)
 * */
public class RunLengthEncoding {

	public static String encoding(String input) {
		if (input == null || input.length() == 0) {
			return input;
		}

		char[] array = input.toCharArray();
		int s = 0;
        int fstart = 0;
        int f = 0;
        int singletons = 0;
        while (f < array.length) {
            while (f < array.length && array[f] == array[fstart]) {
                f++;
            }
            
            // f is out || f != fstart
            array[s++] = array[fstart];
            int count = f - fstart;
            if (count == 1) {
                singletons++;
            } else if (count <= 9) {
                array[s++] = (char) ('0' + count);	
            } else {
                char[] num = ("" + count).toCharArray();
                for (int i = 0; i < num.length; i++) {
                    array[s++] = num[i];
                }
            }
            fstart = f;
        }

        if (singletons == 0) {
            return new String(array, 0, s);
        } else {
            return expandSingletons(array, s, singletons);
        }
	}
	
	// aaaabcca -> a4bc2a -> a4b1c2a1
	// note that array has additional chars not as part of the result [a, 4, b, c, 2, a, c, a]
	// need to pass length so that we know the boundary is [0...length-1]
	private static String expandSingletons(char[] array, int length, int singletons) {
		char[] result = new char[length + singletons];
		int s = result.length - 1;
		int f = length - 1;
		while (f >= 0) {
			if (isLetter(array[f]) && (f == array.length - 1 || isLetter(array[f + 1]))) {
				result[s--] = '1';
			}
			result[s--] = array[f--];			
		}
		return new String(result);	
	}

	public static boolean isLetter(char c) {
		return c < '0' || c > '9';
	}
	
	
	public static void main(String[] args) {
		String s0 = "abc";
		String s1 = "abbcccdeee";
		String s2 = "aaabbc";
		String s3 = "bgkpdizrcbxkimvciwdmsvadpkealwngpzkvhakcfihjuqhawsolsayrwisjlshwsxftbpogugysalrhhmgxcrcykiqhgpryrcmiuzghluwcnrljyojigvajhmlemmnajzbawlcqiwkcdtzwodcsdjqaghmsgejxlzutnejlnimkvxdhzgikquoqonhspkioccsccdrgrwrlmg";
		String s4 = "aaaaaaaaaaaanneeeeeeefffffffwwwwwwwwwwwwwwfffhhhhhhhhhhhhhhhheeeeeeeeeeeeeeedddddddddddddddddddddddddddddgggggggggggggggggggggllllllllllllllllllvvvvvvvvvvvjggggggggggggggggggggccccccccccccccccjjjjttttttttttttttttttttttttttttttmdddkkkkkkkkkkkkkkkkkooooooooooooooooooooaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaahhhhhhhhhhhhhhhhhhhhyyyyyyyyyyyyyyyyyyyyyyyyyyyyoooooooooooooohhhhhelnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjuuuuuuuuuuuuffffffffffffffffffffffffaaaaaaaaaaaaaaaaaaaappppppppppppppppppppppppppppppfffffffffffffffffffffffffffffggeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeevvvvvvvvvvvvvvveeeeeeeeeeeeeeeeeeeeeeellllllllllllllllllllaaaaaaiiiiiiiiiiiiillllgggggggggggggggggggggggggggg";
		System.out.println(encoding(s0));
		System.out.println(encoding(s1));
		System.out.println(encoding(s2));
		System.out.println(encoding(s3));
		System.out.println(encoding(s4));
	}
}
