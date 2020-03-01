# -*- coding: utf-8 -*-
import re
import os
import tempfile
import sys

class Properties:

    def __init__(self, file_name):
        self.file_name = file_name
        self.properties = {}
        try:
            fopen = open(self.file_name, 'r')
            for line in fopen:
                line = line.strip()
                if line.find('=') > 0 and not line.startswith('#'):
                    strs = line.split('=')
                    self.properties[strs[0].strip()] = strs[1].strip()
        except Exception, e:
            raise e
        else:
            fopen.close()

    def has_key(self, key):
        return key in self.properties

    def get(self, key, default_value=''):
        if key in self.properties:
            return self.properties[key]
        return default_value

    def put(self, key, value):
        self.properties[key] = value
        replace_property(self.file_name, key + '=.*', key + '=' + value, True)


def parse(file_name):
    return Properties(file_name)


def replace_property(file_name, from_regex, to_str, append_on_not_exists=True):
    tmpfile = tempfile.TemporaryFile()

    if os.path.exists(file_name):
        r_open = open(file_name, 'r')
        pattern = re.compile(r'' + from_regex)
        found = None
        for line in r_open:
            if pattern.search(line) and not line.strip().startswith('#'):
                found = True
                line = re.sub(from_regex, to_str, line)
            tmpfile.write(line)
        if not found and append_on_not_exists:
            tmpfile.write('\n' + to_str)
        r_open.close()
        tmpfile.seek(0)

        content = tmpfile.read()

        if os.path.exists(file_name):
            os.remove(file_name)

        w_open = open(file_name, 'w')
        w_open.write(content)
        w_open.close()

        tmpfile.close()
    else:
        print "file %s not found" % file_name

def readDb1Properties():

    file_path = os.path.abspath(sys.path[0]+"/../db.properties")
    dict={}
    props = parse(file_path)   #读取文件
    url=props.get('jdbc.url')
    currentSchema=props.get('currentSchema')
    dict["currentSchema"]=currentSchema
    ip=url[url.find("//")+2:url.rindex("/")].split(":")[0]
    port=url[url.find("//")+2:url.rindex("/")].split(":")[1]
    database=url[url.rindex("/")+1:url.rindex("?")]
    dict["host"]=ip
    dict["port"]=int(port)
    dict["database"]=database
    dict["user"]=props.get('jdbc.username')
    dict["passwd"]=props.get('jdbc.password')
    return dict

def readDb2Properties():
    file_path = os.path.abspath(sys.path[0]+"/../db.properties")
    dict={}
    props = parse(file_path)   #读取文件
    url=props.get('jdbc.url2')
    currentSchema=props.get('currentSchema2')
    dict["currentSchema"]=currentSchema
    ip=url[url.find("//")+2:url.rindex("/")].split(":")[0]
    port=url[url.find("//")+2:url.rindex("/")].split(":")[1]
    database=url[url.rindex("/")+1:url.rindex("?")]
    dict["host"]=ip
    dict["port"]=int(port)
    dict["database"]=database
    dict["user"]=props.get('jdbc.username2')
    dict["passwd"]=props.get('jdbc.password2')
    return dict
