import React from 'react';
import { StyleSheet, View, Platform, Text } from 'react-native';

const TopBar = () => {
    return (
        <View style={styles.bar}>
            <Text>TopBar</Text>
        </View>
    )
}

const styles = StyleSheet.create({
    bar : {
        height: "10%",
        width: "100%",
        paddingLeft: "5%",
        paddingRight : "5%",
        alignItems : "center",
        justifyContent: "center"
    }
})

export default TopBar;