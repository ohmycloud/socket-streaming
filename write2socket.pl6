my $vin = 'LSJA0000000000091';
my $last_meter = 0;  # 当前里程数

react {
    whenever IO::Socket::Async.listen('0.0.0.0', 3333) -> $conn {
        my $fh = open 'events.txt', :w;
        react {
            my Bool:D $ignore = True;

            whenever Supply.interval(5).rotor(1, 1 => 1) {
                $ignore = !$ignore;
            }
    
            whenever Supply.interval(1) {
                next if $ignore;
                $fh.print: sprintf("\{'vin':'%s','createTime':%s,'mileage':%s}\n", $vin, DateTime.now.posix, $last_meter);
                $conn.print: sprintf("\{'vin':'%s','createTime':%s,'mileage':%s}\n", $vin, DateTime.now.posix, $last_meter++);
            }
        
            whenever signal(SIGINT) {
                say "Done.";
                $fh.close;
                done;
            }
        } 
    }
    CATCH {
        default {
            say .^name, ': ', .Str;
            say "handled in $?LINE";
        }
    }
}