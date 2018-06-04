"""
marcomunozperez@uma.es
"""

import glob
import os
import sys


def main(folder):
    os.chdir(folder)
    for file in glob.glob("*.tsv"):
        command = 'mongoimport --db chebi_basic --collection ' \
                  + file[:-4] + \
                  ' --type tsv --file ' \
                  + file + \
                  ' --headerline'
        os.system(command)


if __name__ == '__main__':
    """
    Python program that will import tsv data files into mongoDB.
    """

    if len(sys.argv) != 2:
        print("Usage: python3 mongo_import.py <folder>", file=sys.stderr)
        exit(-1)

    main(sys.argv[1])
